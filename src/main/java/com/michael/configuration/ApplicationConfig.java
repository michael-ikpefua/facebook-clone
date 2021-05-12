package com.michael.configuration;


import com.michael.filters.InvalidUserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public FilterRegistrationBean<InvalidUserFilter> filterRegistrationBean() {
        FilterRegistrationBean <InvalidUserFilter> registrationBean = new FilterRegistrationBean();
        InvalidUserFilter invalidUserFilter = new InvalidUserFilter();

        registrationBean.setFilter(invalidUserFilter);
        registrationBean.addUrlPatterns("/dashboard", "/post", "/post/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }


}
