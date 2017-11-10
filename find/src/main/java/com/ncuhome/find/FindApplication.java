package com.ncuhome.find;

import com.ncuhome.find.security.HttpFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.WebApplicationInitializer;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class FindApplication
  //      extends SpringBootServletInitializer implements WebApplicationInitializer

{
    public static void main(String[] args) {
        SpringApplication.run(FindApplication.class, args);
    }

/*
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FindApplication.class);
    }
*/



    @Bean
    public FilterRegistrationBean basicFilterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        HttpFilter httpFilter = new HttpFilter();
        registrationBean.setFilter(httpFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/api/status");
        urlPatterns.add("/api/newFound");
        urlPatterns.add("/api/founds");
        urlPatterns.add("/api/found");
        urlPatterns.add("/api/token");
        urlPatterns.add("/api/password");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }


    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return (container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");

            container.addErrorPages(error401Page, error404Page, error500Page);
        });
    }
}


