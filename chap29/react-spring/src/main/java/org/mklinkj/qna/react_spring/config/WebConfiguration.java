package org.mklinkj.qna.react_spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration()
@EnableWebMvc
// 웹 MVC 설정에서는 Controller만 스캔하게 했다.
@ComponentScan(
    basePackages = "org.mklinkj.qna.react_spring",
    includeFilters = @Filter(type = FilterType.ANNOTATION, value = Controller.class))
public class WebConfiguration implements WebMvcConfigurer {
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("forward:/index.html");
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/**") //
        .addResourceLocations("/statics/");
  }
}
