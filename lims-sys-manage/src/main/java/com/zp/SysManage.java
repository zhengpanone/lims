package com.zp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 20:59.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@SpringBootApplication
public class SysManage {
    public static void main(String[] args) {
        SpringApplication.run(SysManage.class, args);
    }

}
