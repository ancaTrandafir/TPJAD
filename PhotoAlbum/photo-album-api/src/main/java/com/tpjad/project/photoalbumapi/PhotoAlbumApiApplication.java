package com.tpjad.project.photoalbumapi;

import com.tpjad.project.photoalbumapi.config.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PhotoAlbumApiApplication implements WebMvcConfigurer {

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/rest/*");

		return registrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(com.tpjad.project.photoalbumapi.PhotoAlbumApiApplication.class, args);
	}

}
