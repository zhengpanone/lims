package com.zp.lims.sys.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMVCConfig
 * WebMvcConfigurerAdapter配置加入Cors的跨域
 * @author zhengpanone
 * @since 2022-11-11
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域配置所有的域名ip都可以
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "DELETE", "PUT") ;
        //registry.addMapping("/**").allowedOrigins("http://localhost");
        //开发8080
        //registry.addMapping("/**").allowedOrigins("http://localhost:8080");

    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

    }
}
