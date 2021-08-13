package com.zp.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * Swagger properties
 * @author zheng
 */
@Component
@ConfigurationProperties("swagger")
@Data
public class SwaggerProperties {
    /**
     * 是否开启swagger, 生产环境一般关闭
     */
    private Boolean enable;
    /**
     * 项目应用名
     */
    private String applicationName;
    /**
     * 项目版本信息
     */
    private String applicationVersion;
    /**
     * 项目描述信息
     */
    private String applicationDescription;
    /**
     * 接口调试地址
     */
    private String tryHost;

}
