package com.zxy.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//Interceptor Configurator
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()) //declare login interceptor
                .addPathPatterns("/admin/**") //block every page that ends with admin
                //exclude pages required for normal login
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
