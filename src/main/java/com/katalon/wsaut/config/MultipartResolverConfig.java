package com.katalon.wsaut.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class MultipartResolverConfig {
  @Bean
  public CommonsMultipartResolver multipartResolver() {
    return new CommonsMultipartResolver();
  }

  @Bean
  public CommonsMultipartResolver filterMultipartResolver() {
    final CommonsMultipartResolver resolver = new CommonsMultipartResolver();
    return resolver;
  }
}

