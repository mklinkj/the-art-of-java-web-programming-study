package org.mklinkj.taojwp.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfig {
  @Bean
  TilesConfigurer tilesConfigurer() {
    TilesConfigurer configurer = new TilesConfigurer();
    configurer.setDefinitions("classpath:tiles/*.xml");
    configurer.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
    return configurer;
  }

  @Bean
  UrlBasedViewResolver viewResolver() {
    UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
    tilesViewResolver.setViewClass(TilesView.class);
    tilesViewResolver.setOrder(0);
    return tilesViewResolver;
  }
}
