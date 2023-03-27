package com.sgcc.code.common.config;

import com.sgcc.code.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 自定义配置类实现JavaBean注解形式配置
 */
@Configuration
public class WebConfigurationConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")//拦截路径
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**",//swagger放行配置
                "/user/login","/user/info","/versions/latestversion");//业务相关放行配置
                //"/css/**","/demo/**","/fonts/**","/images/**","/js/**","/data/**",
                //"/login.html","/user/**","/editpwd.html","/profile.html","/error.html","/error"
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }

    //@Override
    //public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //    registry.addResourceHandler("swagger-ui.html")
    //            .addResourceLocations("classpath:/META-INF/resources/");
    //    registry.addResourceHandler("/webjars/**")
    //            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    //}

    //@Override
    //public void addViewControllers(ViewControllerRegistry registry) {
    //    registry.addViewController("/").setViewName("redirect:/index.html");
    //    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    //}


}