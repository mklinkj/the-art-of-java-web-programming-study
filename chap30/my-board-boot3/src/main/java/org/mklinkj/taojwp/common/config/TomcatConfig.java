package org.mklinkj.taojwp.common.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TomcatConfig {

  @Bean
  TomcatServletWebServerFactory tomcatFactory() {
    TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();

    tomcatFactory.addContextCustomizers(
        customizer -> customizer.setAllowCasualMultipartParsing(true));

    return tomcatFactory;
  }
}
