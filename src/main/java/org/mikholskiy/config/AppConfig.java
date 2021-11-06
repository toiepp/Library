package org.mikholskiy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan("org.mikholskiy")
public class AppConfig implements WebMvcConfigurer {
  @Bean
  public FreeMarkerViewResolver freeMarkerViewResolver() {
    FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
    resolver.setPrefix("");
    resolver.setSuffix(".ftl");
    resolver.setCache(true);
    return resolver;
  }

  @Bean
  public FreeMarkerConfigurer freeMarkerConfigurer() {
    FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
    configurer.setTemplateLoaderPath("/WEB-INF/views/");
    return configurer;
  }
}
