package com.zp.lims.common.utils;


import lombok.extern.slf4j.Slf4j;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author : zhengpanone
 * Date : 2025/6/24 09:45
 * Version : v1.0.0
 * Description: OSHI（Operating System and Hardware Information）获取系统信息
 */
@Slf4j
public class SystemInfoUtil {
    private static final SystemInfo systemInfo = new SystemInfo();

    public static String getOperatingSystem() {
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        return operatingSystem.toString();
    }

    public static String getCpu() {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        return processor.toString();
    }

    public static Map<String, Object> getMemoryInfo() {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", memory.getTotal());
        map.put("available", memory.getAvailable());
        return map;
    }

    public static String getDiskInfo() {
        List<HWDiskStore> diskStores = systemInfo.getHardware().getDiskStores();
        StringBuilder builder = new StringBuilder();
        for (HWDiskStore disk : diskStores) {
            System.out.println("磁盘名称：" + disk.getName());
            System.out.println("型号：" + disk.getModel());
            System.out.println("序列号：" + disk.getSerial());
            System.out.println("大小：" + (disk.getSize() / 1024 / 1024 / 1024) + " GB");
            System.out.println("读取次数：" + disk.getReads());
            System.out.println("写入次数：" + disk.getWrites());
            System.out.println("已读取字节数：" + disk.getReadBytes());
            System.out.println("已写入字节数：" + disk.getWriteBytes());
            System.out.println();

            builder.append(String.format("磁盘: %s, 总大小: %s, 已读: %s, 已写: %s%n", disk.getModel(),
                    FormatUtil.formatBytes(disk.getSize()),
                    FormatUtil.formatBytes(disk.getReadBytes()),
                    FormatUtil.formatBytes(disk.getWriteBytes())));
        }
        return builder.toString();
    }

    public static String getNetworkInfo() {
        List<NetworkIF> networkIFs = systemInfo.getHardware().getNetworkIFs();
        StringBuilder sb = new StringBuilder();
        for (NetworkIF net : networkIFs) {
            net.updateAttributes(); // 刷新接口统计信息
            System.out.println("名称：" + net.getName());
            System.out.println("显示名称：" + net.getDisplayName());
            System.out.println("MAC 地址：" + net.getMacaddr());
            System.out.println("IPv4：" + String.join(", ", net.getIPv4addr()));
            System.out.println("IPv6：" + String.join(", ", net.getIPv6addr()));
            System.out.println("已发送字节数：" + net.getBytesSent());
            System.out.println("已接收字节数：" + net.getBytesRecv());
            System.out.println("已发送数据包数：" + net.getPacketsSent());
            System.out.println("已接收数据包数：" + net.getPacketsRecv());
            System.out.println();

            sb.append(String.format("网卡: %s, MAC地址: %s, IP: %s%n",
                    net.getName(),
                    net.getMacaddr(),
                    String.join(",", net.getIPv4addr())));
        }
        return sb.toString();
    }

    /**
     * 传感器信息
     */
    public static void getMemoryUsage() {
        // 传感器信息
        Sensors sensors = systemInfo.getHardware().getSensors();
        System.out.println("\n=== 传感器信息 ===");
        System.out.println("CPU 温度：" + sensors.getCpuTemperature() + " °C");
        System.out.println("CPU 风扇转速：" + sensors.getFanSpeeds()[0] + " RPM（如果可用）");
        System.out.println("CPU 电压：" + sensors.getCpuVoltage() + " V");
    }

    public static String getCpuUsage() throws InterruptedException {
        CentralProcessor processor = systemInfo.getHardware().getProcessor();
        //计算两组刻度之间的 CPU 使用率百分比
        // 捕获 CPU 刻度以测量 1 秒内的 CPU 负载
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        // 休眠 1 秒以计算两次刻度之间的 CPU 使用率
        TimeUnit.SECONDS.sleep(1);
        double cpuLoad = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
        return String.format("CPU使用率: %.2f%%", cpuLoad);
    }


    public static void main(String[] args) {
        log.info("操作系统: {}", getOperatingSystem());
        log.info("CPU信息: {}", getCpu());
        log.info("内存信息: {}", getMemoryInfo());
        log.info("磁盘信息: {}", getDiskInfo());
        log.info("网络信息: {}", getNetworkInfo());
        log.info("传感器信息:");
        getMemoryUsage();
    }


}
