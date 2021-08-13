package com.zp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 20:59.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@SpringBootApplication
@EntityScan(value = "com.zp.domain") // 配置jpa注解的扫描
public class SysManage {
    public static void main(String[] args) {

        SpringApplication.run(SysManage.class, args);
    }
}
