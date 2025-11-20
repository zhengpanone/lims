package com.zp.lims.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author : zhengpanone
 * Date : 2025/10/23 16:11
 * Version : v1.0.0
 * Description:
 */
public class IDCardUtils {
    // 18位校验位计算权重（从第1位到第17位）
    private static final int[] WEIGHTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 校验码映射：sum % 11 对应的校验字符
    private static final char[] CHECKSUM_MAP = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    // 支持的省/直辖市代码（两位），方便快速初筛；可按需扩展为完整的行政区划码集合
    private static final Set<String> PROVINCE_CODES = new HashSet<>(Arrays.asList(
            "11", "12", "13", "14", "15", // 北京/天津/河北/山西/内蒙古
            "21", "22", "23",           // 辽宁/吉林/黑龙江
            "31", "32", "33", "34", "35", "36", "37", // 上海/江苏/浙江/安徽/福建/江西/山东
            "41", "42", "43", "44", "45", "46",      // 河南/湖北/湖南/广东/广西/海南
            "50", "51", "52", "53", "54",           // 重庆/四川/贵州/云南/西藏
            "61", "62", "63", "64", "65",           // 陕西/甘肃/青海/宁夏/新疆
            "71", "81", "82", "91"                 // 台湾/香港/澳门/国外（91常用于国外）
    ));

    /**
     * 校验身份证号（支持15位和18位）
     *
     * @param id 身份证号（可能含小写x）
     * @return true 如果合法
     */
    public static boolean isValid(String id) {
        if (id == null) return false;
        String trimmed = id.trim().toUpperCase(Locale.ROOT);
        if (trimmed.length() == 15) {
            // 先把 15 位转换为 18 位再校验
            try {
                String converted = convert15To18(trimmed);
                return isValid18(converted);
            } catch (Exception e) {
                return false;
            }
        } else if (trimmed.length() == 18) {
            return isValid18(trimmed);
        } else {
            return false;
        }
    }

    /**
     * 校验 18 位身份证（包含校验位）
     *
     * @param id18 18 位身份证号，字母校验位可为 'X'
     * @return true 如果合法
     */
    public static boolean isValid18(String id18) {
        if (id18 == null) return false;
        String id = id18.trim().toUpperCase(Locale.ROOT);
        if (id.length() != 18) return false;

        // 前17位必须为数字
        for (int i = 0; i < 17; i++) {
            if (!Character.isDigit(id.charAt(i))) return false;
        }

        // 最后一位可以是数字或 'X'
        char last = id.charAt(17);
        if (!(Character.isDigit(last) || last == 'X')) return false;

        // 校验行政区码（前2位快速校验）
        String province = id.substring(0, 2);
        if (!isValidProvinceCode(province)) return false;

        // 校验出生日期：第7-14位，格式 yyyyMMdd
        String birthStr = id.substring(6, 14);
        if (!isValidDate(birthStr, "yyyyMMdd")) return false;

        // 计算并校验校验位
        char expect = calculateCheckDigit(id.substring(0, 17));
        return expect == last;
    }

    /**
     * 将 15 位身份证转换为 18 位（通过在年份中间插入 "19" 或更保守的逻辑）
     * 注意：15位身份证本质上是老规则，只是做兼容。
     *
     * @param id15 15 位身份证
     * @return 对应的 18 位身份证（最后一位为校验位）
     * @throws IllegalArgumentException 如果 id15 格式不正确
     */
    public static String convert15To18(String id15) {
        if (id15 == null) throw new IllegalArgumentException("id15 is null");
        String id = id15.trim();
        if (id.length() != 15) throw new IllegalArgumentException("长度不是15位");

        // 前6位为行政区码，7-12位为yyMMdd，后3位为顺序码
        for (int i = 0; i < 15; i++) {
            if (!Character.isDigit(id.charAt(i))) throw new IllegalArgumentException("15位身份证必须全为数字");
        }

        String province = id.substring(0, 2);
        if (!isValidProvinceCode(province)) {
            // 不抛出错误也可以，但这里做提示性错误
            throw new IllegalArgumentException("疑似无效的行政区码: " + province);
        }

        // 将 yy 转换为 yyyy，通常插入 "19"（因为15位多是1900年代发的）
        String birth6 = id.substring(6, 12); // yyMMdd
        String yearPrefix = "19";
        String fullBirth = yearPrefix + birth6; // yyyyMMdd

        if (!isValidDate(fullBirth, "yyyyMMdd")) {
            throw new IllegalArgumentException("转换后的出生日期无效: " + fullBirth);
        }

        String body17 = id.substring(0, 6) + fullBirth + id.substring(12, 15);
        char checksum = calculateCheckDigit(body17);
        return body17 + checksum;
    }

    /**
     * 计算 17 位数字对应的校验位
     *
     * @param first17 前17位数字字符串
     * @return 校验位字符（'0'-'9'或 'X'）
     */
    public static char calculateCheckDigit(String first17) {
        if (first17 == null || first17.length() != 17) {
            throw new IllegalArgumentException("first17 must be length 17");
        }
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            char c = first17.charAt(i);
            if (!Character.isDigit(c)) throw new IllegalArgumentException("first17 必须全为数字");
            int digit = c - '0';
            sum += digit * WEIGHTS[i];
        }
        int mod = sum % 11;
        return CHECKSUM_MAP[mod];
    }

    /**
     * 简单校验省/直辖市代码（两位）。如果需要更严格，可以扩展完整的6位区划码集合。
     */
    public static boolean isValidProvinceCode(String twoDigits) {
        if (twoDigits == null || twoDigits.length() != 2) return false;
        return PROVINCE_CODES.contains(twoDigits);
    }

    /**
     * 判断日期字符串是否为合法日期且不在未来
     *
     * @param dateStr 日期字符串
     * @param pattern 模式，例如 "yyyyMMdd"
     * @return true 合法
     */
    public static boolean isValidDate(String dateStr, String pattern) {
        if (dateStr == null || pattern == null) return false;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDate d = LocalDate.parse(dateStr, fmt);
            LocalDate today = LocalDate.now();
            // 合法范围可以按需调整，例如不接受早于1900年的身份证
            if (d.isAfter(today)) return false;
            if (d.getYear() < 1900) return false;
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    /**
     * 从身份证号中提取出生日期（返回 LocalDate），支持15/18位
     *
     * @param id 身份证号
     * @return LocalDate 或 null（无效时）
     */
    public static LocalDate getBirthDate(String id) {
        if (id == null) return null;
        String t = id.trim().toUpperCase(Locale.ROOT);
        try {
            if (t.length() == 18) {
                String birth = t.substring(6, 14); // yyyyMMdd
                return LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
            } else if (t.length() == 15) {
                String birth = "19" + t.substring(6, 12); // yyMMdd -> 19yyMMdd
                return LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));
            } else {
                return null;
            }
        } catch (DateTimeParseException ex) {
            return null;
        }
    }

    /**
     * 从身份证号中提取性别（M/F），顺序码奇数为男性，偶数为女性。返回 "M" 或 "F"，无效时返回 null。
     */
    public static String getGender(String id) {
        if (id == null) return null;
        String t = id.trim();
        if (t.length() == 18) {
            char c = t.charAt(16); // 顺序码第3位
            if (!Character.isDigit(c)) return null;
            int seq = c - '0';
            return (seq % 2 == 1) ? "M" : "F";
        } else if (t.length() == 15) {
            char c = t.charAt(14); // 15位最后一位为顺序码的第三位
            if (!Character.isDigit(c)) return null;
            int seq = c - '0';
            return (seq % 2 == 1) ? "M" : "F";
        } else {
            return null;
        }
    }

    /**
     * 生成一个合法的 18 位身份证号
     * @param areaCode 6位行政区划码（如果为 null 或长度不为6，则会尝试用 provinceCode 补全）
     * @param birth 出生日期（yyyy-MM-dd）
     * @param sequence 顺序码（1-999），如果 <=0 则随机生成
     * @return 18 位身份证号（包含正确校验位）
     */
    public static String generate18(String areaCode, LocalDate birth, int sequence) {
        if (birth == null) throw new IllegalArgumentException("birth 不能为空");
        String birthStr = birth.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        String area = sanitizeAreaCode(areaCode);
        int seq = sequence;
        if (seq <= 0) {
            seq = ThreadLocalRandom.current().nextInt(1, 1000); // 1 - 999
        } else if (seq > 999) {
            throw new IllegalArgumentException("sequence 必须在 1-999");
        }

        String seqStr = String.format("%03d", seq);
        String first17 = area + birthStr + seqStr;
        char check = calculateCheckDigit(first17);
        return first17 + check;
    }

    /**
     * 将 18 位身份证转换为 15 位（仅在需要返回15位身份证时使用）
     * 注：15位身份证没有校验位，且年份缩短为两位
     */
    public static String to15From18(String id18) {
        if (id18 == null || id18.length() != 18) throw new IllegalArgumentException("id18 必须为18位");
        // 前6位 + yyMMdd + 后3位（去掉校验位）
        String yyMMdd = id18.substring(8, 14); // yyyyMMdd -> take yyMMdd (last 6)
        String yy = yyMMdd.substring(2); // get yyMMdd? careful: we want yyMMdd -> take substring(2) gives yyMMdd? Actually:
        // Simpler implement:
        String birthFull = id18.substring(6, 14); // yyyyMMdd
        String birth15 = birthFull.substring(2); // yyMMdd
        return id18.substring(0, 6) + birth15 + id18.substring(14, 17);
    }



    private static String sanitizeAreaCode(String areaCode) {
        if (areaCode != null && areaCode.matches("\\d{6}")) {
            return areaCode;
        } else {
            // 如果传入的不是6位区域码，尝试用前两位省码补全为 "xx0000"
            if (areaCode != null && areaCode.length() >= 2) {
                String prov = areaCode.substring(0, 2);
                if (isValidProvinceCode(prov)) {
                    return prov + "0000";
                }
            }
            // 默认使用 "110000"（北京市）作为兜底区划码
            return "110000";
        }
    }

    /**
     * 批量生成身份证号
     *
     * @param count 要生成的数量（必须 > 0）
     * @param birthStart 出生日期范围开始（包含）
     * @param birthEnd 出生日期范围结束（包含）
     * @param provinceCodes 可选的省级代码列表（两位，例如 "11"、"44"）。如果为 null 或空，则使用全部支持的省码集合。
     * @param return15 如果 true，则在生成后把 18 位转换为 15 位返回（注意：只有在出生年份为 19xx 时转换才通常合理）
     * @param ensureUnique 如果 true，确保返回结果唯一（去重），若无法生成足够数量会抛出异常
     * @return List<String> 身份证列表（长度等于 count，或者抛出异常）
     */
    public static List<String> generateBatchIDs(int count,
                                                LocalDate birthStart,
                                                LocalDate birthEnd,
                                                List<String> provinceCodes,
                                                boolean return15,
                                                boolean ensureUnique) {
        if (count <= 0) throw new IllegalArgumentException("count 必须大于 0");
        if (birthStart == null || birthEnd == null) throw new IllegalArgumentException("出生日期范围不能为空");
        if (birthStart.isAfter(birthEnd)) throw new IllegalArgumentException("出生日期范围错误");

        List<String> provs;
        if (provinceCodes == null || provinceCodes.isEmpty()) {
            provs = new ArrayList<>(PROVINCE_CODES);
        } else {
            // 只保留有效两位省码
            provs = new ArrayList<>();
            for (String p : provinceCodes) {
                if (p != null && p.length() == 2 && isValidProvinceCode(p)) provs.add(p);
            }
            if (provs.isEmpty()) provs = new ArrayList<>(PROVINCE_CODES);
        }

        long startEpoch = birthStart.toEpochDay();
        long endEpoch = birthEnd.toEpochDay();

        Set<String> resultSet = new LinkedHashSet<>();
        List<String> resultList = new ArrayList<>(count);

        int attempts = 0;
        int maxAttempts = Math.max(1000, count * 20); // 防止无限循环：尝试上限

        while ((ensureUnique ? resultSet.size() : resultList.size()) < count && attempts < maxAttempts) {
            attempts++;

            // 随机选择省码并构建6位区划码（用省码 + "0000"）
            String prov = provs.get(ThreadLocalRandom.current().nextInt(provs.size()));
            String area = prov + "0000";

            // 随机出生日期
            long randomDay = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch + 1);
            LocalDate birth = LocalDate.ofEpochDay(randomDay);

            // 随机顺序码 1-999
            int seq = ThreadLocalRandom.current().nextInt(1, 1000);

            String id18 = generate18(area, birth, seq);

            if (return15) {
                // 仅对 18 位能合理转换为 15 位的情况才转换；这里仍然直接转换（兼容处理）
                String id15 = to15From18(id18);
                if (ensureUnique) {
                    if (resultSet.add(id15)) { /* added */ }
                } else {
                    resultList.add(id15);
                }
            } else {
                if (ensureUnique) {
                    if (resultSet.add(id18)) { /* added */ }
                } else {
                    resultList.add(id18);
                }
            }
        }

        if (ensureUnique) {
            if (resultSet.size() < count) {
                throw new IllegalStateException("在给定参数下无法生成足够的唯一身份证号（尝试次数达到上限）。请放宽条件或增加尝试上限。");
            }
            resultList.addAll(resultSet);
        } else {
            if (resultList.size() < count) {
                throw new IllegalStateException("未能生成足够数量的身份证号（尝试次数达到上限）。");
            }
        }

        // 截取前 count 个（以防多余）
        return resultList.subList(0, count);
    }

    // ----- simple demo / 单元测试示例 -----
    public static void main(String[] args) {
        String[] tests = {
                "11010519491231002X", // 示例合法 18 位（北京）
                "110105491231002",    // 合法 15 位（老号）
                "440524188001010014", // 示例可能不真实，仅用于测试格式
                "123456789012345",    // 错误行政区码
                "11010520050230001X", // 出生日期 2005-02-30 不合法
                "320311770706001"     // 15位（江苏） -> 转换后校验
        };

        for (String s : tests) {
            System.out.println("测试: " + s);
            boolean ok = isValid(s);
            System.out.println("  有效: " + ok);
            try {
                if (s.length() == 15) {
                    String conv = convert15To18(s);
                    System.out.println("  转换为18位: " + conv + "  校验: " + isValid18(conv));
                } else if (s.length() == 18) {
                    System.out.println("  校验位计算: " + calculateCheckDigit(s.substring(0, 17)));
                }
            } catch (Exception ex) {
                System.out.println("  转换/计算异常: " + ex.getMessage());
            }
            LocalDate birth = getBirthDate(s);
            System.out.println("  出生: " + (birth == null ? "无法解析" : birth));
            System.out.println("  性别: " + getGender(s));
            System.out.println("-----------------------------------");
        }


        // 示例：生成 10 个出生在 1990-01-01 到 2000-12-31 之间的18位身份证，省码随机，保证唯一
        List<String> ids = generateBatchIDs(
                10,
                LocalDate.of(1990, 1, 1),
                LocalDate.of(2000, 12, 31),
                Arrays.asList("11", "31", "44"), // 可选的省码，示例中为北京/上海/广东
                false, // 返回18位
                true   // 保证唯一
        );

        System.out.println("生成的身份证：");
        ids.forEach(System.out::println);

        // 示例：生成 5 个 15 位身份证（从 18 位转换），出生 1970-01-01 到 1979-12-31
        List<String> ids15 = generateBatchIDs(
                5,
                LocalDate.of(1970, 1, 1),
                LocalDate.of(1979, 12, 31),
                null,  // 使用所有省码
                true,  // 返回15位
                true   // 保证唯一
        );

        System.out.println("生成的15位身份证：");
        ids15.forEach(System.out::println);
    }

}
