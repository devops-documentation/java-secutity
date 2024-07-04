package com.learning.spring.Config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class CorsConfig {
	
	private static final Logger LOGGER = LogManager.getLogger(CorsConfig.class.getName());

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		LOGGER.debug("AllowedOrigins- *");
		configuration.setAllowedMethods(Arrays.asList(HttpMethod.GET.toString(), HttpMethod.POST.toString(),
				HttpMethod.PUT.toString(), HttpMethod.DELETE.toString(),HttpMethod.OPTIONS.toString()));
		LOGGER.debug("AllowedMethod- GET|POST|PUT|DELETE");
		configuration
				.setAllowedHeaders(Arrays.asList("*"/* "Origin", "Content-Type", "X-Auth-Token", "Authorization" */));
		LOGGER.debug("AllowedHeaders- *");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		LOGGER.debug("UrlBasedCorsConfigurationSource- /**");
		return source;
	}
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// Don't do this in production, use a proper list  of allowed origins
		config.setAllowedOrigins(Arrays.asList("http://localhost:3000", "https://console.theja.in"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}