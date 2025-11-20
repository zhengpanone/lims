package com.zp.lims.common.utils;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author : zhengpanone
 * Date : 2025/8/29 15:52
 * Version : v1.0.0
 * Description:
 */


public class YearRangeUtils {

    /**
     * 通用年份区间合并方法（支持动态分组字段数量）
     *
     * @param list            原始对象列表
     * @param yearExtractor   提取年份的函数
     * @param groupExtractors 分组字段提取函数列表（可变数量）
     * @param formatter       格式化输出函数，输入：年份区间字符串、分组字段值列表
     * @param <T>             对象类型
     * @return 格式化字符串（每条一行）
     */
    public static <T> String mergeYearRanges(
            List<T> list,
            Function<T, Integer> yearExtractor,
            List<Function<T, ?>> groupExtractors,
            BiFunction<String, List<Object>, String> formatter
    ) {
        // 分组：Key = List<Object>（各字段值）
        Map<List<Object>, List<Integer>> grouped = list.stream()
                .collect(Collectors.groupingBy(
                        t -> groupExtractors.stream().map(f -> f.apply(t)).collect(Collectors.toList()),
                        Collectors.mapping(yearExtractor, Collectors.toList())
                ));

        List<String> result = new ArrayList<>();

        for (Map.Entry<List<Object>, List<Integer>> entry : grouped.entrySet()) {
            List<Integer> years = entry.getValue().stream()
                    .sorted(Comparator.reverseOrder()).collect(Collectors.toList());

            if (years.isEmpty()) {
                continue; // 跳过空分组
            }

            int start = years.get(0), prev = years.get(0);
            for (int i = 1; i <= years.size(); i++) {
                if (i == years.size() || years.get(i) != prev - 1) {
                    String yearStr = (start == prev) ? String.valueOf(start) : prev + "-" + start;
                    result.add(formatter.apply(yearStr, entry.getKey()));
                    if (i < years.size()) {
                        start = years.get(i);
                    }
                }
                if (i < years.size()) {
                    prev = years.get(i);
                }
            }
        }

        return String.join("\n", result);
    }


    public static void main(String[] args) {

        class A {
            String yearDate;
            String unit;
            String grade;

            public A() {
            }

            public A(String year, String unit, String grade) {
                this.yearDate = year;
                this.unit = unit;
                this.grade = grade;
            }

            public String getYear() {
                return yearDate;
            }

            public String getUnit() {
                return unit;
            }

            public String getGrade() {
                return grade;
            }

            public void setYear(String year) {
                this.yearDate = year;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }
        }

        class B {
            Date date;
            String company;
            String region;
            String level;

            public B() {
            }

            public B(Date date, String company, String region, String level) {
                this.date = date;
                this.company = company;
                this.region = region;
                this.level = level;
            }

            public Date getDate() {
                return date;
            }

            public void setDate(Date date) {
                this.date = date;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getRegion() {
                return region;
            }

            public void setRegion(String region) {
                this.region = region;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }

        List<A> listA = Arrays.asList(
                new A("2025-01-01", "单位A", "优秀"),
                new A("2024-01-01", "单位A", "优秀"),
                new A("2024-01-01", "单位B", "优秀"),
                new A("2023-01-01", "单位A", "一般")
        );
        String resultA = YearRangeUtils.mergeYearRanges(
                listA,
                (item) -> LocalDate.parse(item.getYear()).getYear(),
                Arrays.asList(A::getUnit, A::getGrade),
                (yearStr, fields) -> {
                    return yearStr + "，" + String.join("，", fields.stream().map(Object::toString).collect(Collectors.toList()));
                }
        );

        System.out.println(resultA);

        List<B> listB = Arrays.asList(
                new B(new GregorianCalendar(2025, Calendar.JANUARY, 1).getTime(), "公司X", "华东", "良好"),
                new B(new GregorianCalendar(2024, Calendar.JANUARY, 1).getTime(), "公司X", "华东", "良好"),
                new B(new GregorianCalendar(2023, Calendar.JANUARY, 1).getTime(), "公司Y", "华南", "一般")
        );

        String resultB = YearRangeUtils.mergeYearRanges(
                listB,
                b -> {
                    Calendar c = Calendar.getInstance();
                    c.setTime(b.getDate());
                    return c.get(Calendar.YEAR);
                },
                Arrays.asList(B::getCompany, B::getRegion, B::getLevel),
                (yearStr, fields) -> yearStr + "，" + String.join("，", fields.stream().map(Object::toString).collect(Collectors.toList()))
        );

        System.out.println(resultB);


    }
}
