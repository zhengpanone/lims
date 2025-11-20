package com.zp.lims.sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 20:59.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.zp.lims"}, excludeFilters = @ComponentScan.Filter(
        type = FilterType.REGEX,
        pattern = "com\\.anji\\.captcha\\.controller\\..*"
))
@MapperScan("com.zp.lims.sys.mapper")
public class SysManage {
    public static void main(String[] args) {
        SpringApplication.run(SysManage.class, args);
    }

}
