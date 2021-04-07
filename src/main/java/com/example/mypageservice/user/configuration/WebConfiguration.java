package com.example.mypageservice.user.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.mypageservice.user.security.AuthInterceptor;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**").allowedMethods("*");
	}

	@Bean
	public AuthInterceptor requestHandler() {
		return new AuthInterceptor();
	}

	// 인터셉터를 추가
	@Override
	public void addInterceptors(final InterceptorRegistry registry) {
		registry.addInterceptor(requestHandler());
	}
}
