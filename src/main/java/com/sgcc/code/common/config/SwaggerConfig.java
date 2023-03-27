package com.sgcc.code.common.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zimingl
 * @Date 2022/10/14 23:39
 * @Description: TODO
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        ticketPar.name("authorization").description("平台统一维护的请求令牌")
                .defaultValue("eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNoci9EKwjAMRf8lzw20SZOt-5tWI0zFSVtFEP_dbg8XDodzv3DtKyzgUwmXFE5IXBSjaMZsRXAW8XlsOmsCB-1VRkyD1tYG9e1mD2xW31Z3mTssQT1HViJxYJ_nIeLEdIi63W0_BkfsHdEMvz8AAAD__w.xU96pNAdTfCYP8UR18AVw24pW1LJyHf9z4_c9p4Y2CHryWGLu03o9q0xkcZuKHqFYdN5WZPPbmciTOllZroEkA")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        List<Parameter> pars = new ArrayList<Parameter>();
        pars.add(ticketPar.build());
        return new Docket(DocumentationType.SWAGGER_12)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("数据监控平台")
                .description("API文档")
                .version("1.0")
                .build();
    }
}
