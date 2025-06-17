package com.zp.lims.sys.config;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * http://localhost:8081/swagger-ui.html
 * http://localhost:8081/doc.html
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
@ConditionalOnClass(Docket.class)
public class Swagger2Config {
    private static final String VERSION = "1.0";
    @Value("${springfox.documentation.swagger.v2.enabled}")
    private Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
            .enable(swaggerEnabled)
            .groupName("lims")
            .apiInfo(apiInfo()).select()
            .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) // 加上Api注解的类自动生成接口文档
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("lims接口文档")
                .contact(new Contact("zhengpanone","https://www.github.com/zhengpanone","zhengpanone@hotmail.com"))
                .description("系统接口文档")
                .version(VERSION)
                .build();
    }
}
