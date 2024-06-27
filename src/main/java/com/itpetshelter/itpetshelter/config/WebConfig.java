package com.itpetshelter.itpetshelter.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean<DateToLocalDateTimeFilter> loggingFilter(){
        FilterRegistrationBean<DateToLocalDateTimeFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new DateToLocalDateTimeFilter());
        registrationBean.addUrlPatterns("/itpetshelter/*"); // 필터를 적용할 URL 패턴 설정

        return registrationBean;
    }
}