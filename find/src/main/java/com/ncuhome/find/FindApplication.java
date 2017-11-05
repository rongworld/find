package com.ncuhome.find;

import com.ncuhome.find.security.UserFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@EnableAutoConfiguration
public class FindApplication {
	public static void main(String[] args) {
		SpringApplication.run(FindApplication.class, args);
	}

//@Bean
	public FilterRegistrationBean basicFilterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		UserFilter userFilter = new UserFilter();
		registrationBean.setFilter(userFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/addLost");
		urlPatterns.add("/queryLost");
		urlPatterns.add("/confirm");
		urlPatterns.add("/display");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}
}


