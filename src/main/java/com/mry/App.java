package com.mry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.mry.config.Starter;

@SpringBootApplication
public class App extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@Bean 
    public ServletRegistrationBean<Starter> getServletRegistrationBean() { 
        ServletRegistrationBean<Starter> bean = new ServletRegistrationBean<Starter>(new Starter());
        bean.addUrlMappings("/hyzx");
        return bean;
    }
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(App.class);
    }
}
