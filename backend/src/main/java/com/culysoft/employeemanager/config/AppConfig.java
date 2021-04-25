package com.culysoft.employeemanager.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class AppConfig {
	
	@Bean
	public CorsFilter corsFilter() {
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://app-employee-manager-culysoft.herokuapp.com"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type", 
				"Accept", "Authorization", "Origin, Accept", "X-Request-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers", "Access-Control-Allow-Origin"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization", 
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Controll-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
