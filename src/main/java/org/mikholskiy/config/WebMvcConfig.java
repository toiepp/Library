package org.mikholskiy.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
@PropertySource("classpath:application.yaml")
@ComponentScan("org.mikholskiy")
public class WebMvcConfig implements WebMvcConfigurer {
	@Bean
	public InternalResourceViewResolver viewResolver(@Value("${resolver.prefix}") String prefix,
																									 @Value("${resolver.suffix}") String suffix) {
		var resolver = new InternalResourceViewResolver();
		resolver.setPrefix(prefix);
		resolver.setSuffix(suffix);
		resolver.setCache(true);
		return resolver;
	}
}