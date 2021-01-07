package com.example.numbergenerator;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class NumberGenerationConfiguration {

  @Bean
  public DozerBeanMapper mapper() {
    return new DozerBeanMapper();
  }

}
