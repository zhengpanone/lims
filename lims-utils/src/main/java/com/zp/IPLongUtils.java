package com.zp;

public class IPLongUtils {

    /**
     * 把字符串IP转换成Long
     * 
     * @param ipStr 字符串IP值
     * @return IP对应的long值
     */
    public static long IP2Long(String ipStr) {
        String[] ip = ipStr.split("\\.");
        return (Long.valueOf(ip[0]) << 24) + (Long.valueOf(ip[1]) << 16) + (Long.valueOf(ip[2]) << 8)
                + Long.valueOf(ip[3]);
    }

    /**
     * 把IP的long值转换成字符串
     * 
     * @param IPLong IP的long值
     * @return long值对应的字符串
     */
    public static String long2IP(long IPLong) {
        StringBuilder ip = new StringBuilder();
        ip.append(IPLong >>> 24).append(".");
        ip.append((IPLong >>> 16) & 0xFF).append(".");
        ip.append((IPLong >>> 8) & 0xFF).append(".");
        ip.append(IPLong & 0xFF);
        return ip.toString();
    }

    public static void main(String[] args) {
        System.out.println(IP2Long("192.168.0.1"));
        System.out.println(long2IP(3232235521L));

    }
}
