package com.zp;

import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * @Author: zhengpanone
 * @Description: 是把一个 64 位的 long型的id，1 个 bit是不用的，用其中的41 bit作为毫秒数，用10 bit作为工作机器 id，12 bit作为序列号。
 * 1 bit：不用，为啥呢？因为二进制里第一个 bit 为如果是 1，那么都是负数，但是我们生成的 id 都是正数，所以第一个 bit 统一都是 0。
 * 41 bit：表示的是时间戳，单位是毫秒。41 bit 可以表示的数字多达 2^41 - 1，也就是可以标识 2^41 - 1 个毫秒值，换算成年就是表示69年的时间。
 * 10 bit：记录工作机器 id，代表的是这个服务最多可以部署在 2^10台机器上哪，也就是1024台机器。但是 10 bit 里 5 个 bit 代表机房 id，5 个 bit 代表机器 id。意思就是最多代表 2^5个机房（32个机房），每个机房里可以代表 2^5 个机器（32台机器）。
 * 12 bit：这个是用来记录同一个毫秒内产生的不同 id，12 bit 可以代表的最大正整数是 2^12 - 1 = 4096，也就是说可以用这个 12 bit 代表的数字来区分同一个毫秒内的 4096 个不同的 id。
 * <p>
 * 0 | 0001100 10100010 10111110 10001001 01011100 00 | 10001 | 1 1001 | 0000 00000000
 * @Date:Created in 2021/08/07 8:39.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Component
public class IdWorker {
    /**
     * 时间起始标记点,作为基准,一般取系统最近时间(一旦确定不能变动)
     */
    private final static long twepoch = 1288834974657L;
    /**
     * 机器标识位数
     */
    private final static long workerIdBits = 5L;
    /**
     * 数据中心标识位数
     */
    private final static long datacenterIdBits = 5L;

    /**
     * 机器ID最大值
     * 这个是二进制运算，就是 5 bit最多只能有31个数字，也就是说机器id最多只能是32以内
     */

    private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 数据中心ID最大值
     * 5bit最多只能有31个数字，机房id最多只能是32以内
     */
    private final static long maxDatacenterId = -1L ^ (-1 << datacenterIdBits);
    /**
     * 毫秒内自增位
     */
    private final static long sequenceBits = 12L;
    /**
     * 机器ID偏左移12位
     */
    private final static long workerIdShift = sequenceBits;
    /**
     * 数据中心左移17位
     */
    private final static long datacenterIdShift = sequenceBits + workerIdBits;
    /**
     * 时间毫秒左移22位
     */
    private final static long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;


    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);
    /**
     * 上次生产id的时间戳
     */
    private static long lastTimestamp = -1L;
    /**
     * 0并发控制
     */
    private long sequence = 0L;

    private final long workerId;
    /**
     * 数据标识id部分
     */
    private final long datacenterId;

    public IdWorker() {
        this.datacenterId = getDatacenterId(maxDatacenterId);
        this.workerId = getMaxWorkerId(datacenterId, maxDatacenterId);
    }

    public IdWorker(long workerId, long datacenterId, long sequence) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        System.out.printf("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d,sequence bits %d, workerId %d",
                timestampLeftShift, datacenterIdBits, workerIdBits, sequenceBits, workerId);
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        this.sequence = sequence;
    }


    public long getWorkerId() {
        return workerId;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }


    /**
     * 获取maxWorkerId
     *
     * @param datacenterId
     * @param maxWorkerId
     * @return
     */
    protected static long getMaxWorkerId(long datacenterId, long maxWorkerId) {
        StringBuffer mpid = new StringBuffer();
        mpid.append(datacenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if (!name.isEmpty()) {
            mpid.append(name.split("@")[0]);
        }
        return (mpid.toString().hashCode() & 0xfff) % (maxWorkerId + 1);
    }

    /**
     * 数据标识ID
     *
     * @return
     */
    public long getDatacenterId(long maxDatacenterId) {
        long id = 0L;
        try {
            InetAddress ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            if (network == null) {
                id = 1L;
            } else {
                byte[] mac = network.getHardwareAddress();
                id = ((0x000000FF & (long) mac[mac.length - 1])
                        | (0x0000FF00 & (((long) mac[mac.length - 2]) << 8))) >> 6;
                id = id % (maxDatacenterId + 1);
            }
        } catch (Exception e) {
            System.out.println(" getDatacenterId: " + e.getMessage());
        }
        return id;
    }

    public long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.err.printf("clock is moving backwards. Rejecting requests until %d", lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        if (lastTimestamp == timestamp) {
            //当前毫秒内则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计数满了,则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID,并返回ID
        return ((timestamp - twepoch) << timestampLeftShift)
                | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }


    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }


    public static void main(String[] args) {
        IdWorker worker = new IdWorker(1, 1, 1);
        for (int i = 0; i < 30; i++) {
            System.out.println(worker.nextId());
        }
    }
}
