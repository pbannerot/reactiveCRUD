package com.esolution.reactive.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

@Configuration
@EnableWebFlux
public class CorsConfiguration implements WebFluxConfigurer {
		@Override
	    public void addCorsMappings(@NonNull CorsRegistry registry) {
	        registry.addMapping("/**").allowedOrigins("*");
	    }
}
