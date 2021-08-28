package spring.swagger.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Gary
 */
@EnableOpenApi
@Configuration
public class SwaggerConfig {

    @Value("${springfox.documentation.swagger-ui.enabled}")
    private boolean enabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(
                // 規範設置
                DocumentationType.OAS_30)
                //  Swagger 開關
                .enable(enabled)
                // 基本資料
                .apiInfo(apiInfo())
                // group
                .groupName("第一組")
                // 想要產生文件的路徑
                .select()
                // 對所有 api 監控
                .apis(RequestHandlerSelectors.any())
                //.apis(RequestHandlerSelectors.basePackage("spring.swagger.example.controller"))
                // 監控路徑
                .paths(PathSelectors.any())
                // 忽略路徑api開頭的路徑
                .paths(PathSelectors.regex("/error.*").negate())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 標題
                .title("書籍使用")
                // 說明
                .description("書籍open api 3.0說明文件")
                // 版本
                .version("0.0.1")
                // 聯絡資料
                .contact(new Contact("Gary", "", "aq715086@gmail.com"))
                .build();
    }

}