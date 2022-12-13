package com.zp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 20:59.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@SpringBootApplication
@MapperScan("com.zp.sys.mapper")
public class SysManage {
    public static void main(String[] args) {
        SpringApplication.run(SysManage.class, args);
    }

}
