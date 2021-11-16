package com.zp.configuration;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Swagger 配置类
 * @author zheng
 */
@EnableOpenApi
@Configuration
public class SwaggerConfiguration implements WebMvcConfigurer {
    @Autowired
    private  SwaggerProperties swaggerProperties;

    public SwaggerConfiguration(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket createRestApi() {
        // swagger 设置,基本信息,要解析的接口及路径
        return new Docket(DocumentationType.OAS_30).pathMapping("/")
                // 定义是否开启swagger,false为关闭,可以通过变量控制
                .enable(swaggerProperties.getEnable())
                // 将api的元信息设置为包含在json ResourceListing响应中
                .apiInfo(apiInfo())
                // 接口调试地址
                .host(swaggerProperties.getTryHost())
                // 选择哪些接口作为swagger的doc发布
                .select()
                //设置通过什么方式定位需要自动生成文档的接口，
                //.apis(RequestHandlerSelectors.any())
                // .apis(RequestHandlerSelectors.basePackage("com.zp"))
                //这里定位方法上的@ApiOperation注解
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //接口URI路径设置，any是全路径，也可以通过PathSelectors.regex()正则匹配
                .paths(PathSelectors.any()).build();
                // 支持的通讯协议集合
                //.protocols(newHashSet("https", "http")).securitySchemes(securitySchemes())
                // 授权信息全局应用
                //.securityContexts(securityContexts());

    }

    /**
     * API 页面上半部分展示信息
     * 生成接口信息，包括标题、联系人，联系方式等
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title(swaggerProperties.getApplicationDescription() + "Api Doc")
                // 描述信息
                .description(swaggerProperties.getApplicationDescription())
                // 创建人
                .contact(new Contact("zhengpanone", null, "zhengpanone@hotmail.com"))
                // 版本号
                .version("Application Version" + swaggerProperties.getApplicationVersion() + ", Spring Boot Vesion"
                        + SpringBootVersion.getVersion())
                .build();
    }

    /*private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }*/

    /**
     * 授权信息全局应用
     * 
     * @return
     */
    /*private List<SecurityContext> securityContexts() {
        return Collections.singletonList(SecurityContext.builder().securityReferences(Collections.singletonList(
                new SecurityReference("BASE_TOKEN", new AuthorizationScope[] { new AuthorizationScope("global", "") })))
                .build());
    }*/

    /*@SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }*/

    /**
     * 通用拦截器排除swagger设置,所有拦截器都会自动加swagger相关资源排除信息
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        try {

            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils
                    .getField(registrationsField, registry);

            if (registrations != null) {
                for (InterceptorRegistration interceptorRegistration : registrations) {
                    interceptorRegistration.excludePathPatterns("/swagger**/**").excludePathPatterns("/webjars/**")
                            .excludePathPatterns("/v3/**").excludePathPatterns("/doc.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
