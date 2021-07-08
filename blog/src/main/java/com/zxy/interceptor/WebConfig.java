package com.zxy.interceptor;

//dependencies
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //register a new login interceptor
        registry.addInterceptor(new LoginInterceptor())
                //block pages that leads to admin pages
                .addPathPatterns("/admin/**")
                //exclude pages required for normal login
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }
}
