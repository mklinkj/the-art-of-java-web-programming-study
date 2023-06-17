package org.mklinkj.taojwp.mail;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class EmailTemplateConfig {
  @Bean
  public TemplateEngine emailTemplateEngine() {
    final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    templateEngine.addTemplateResolver(htmlTemplateResolver());
    return templateEngine;
  }

  private ITemplateResolver htmlTemplateResolver() {
    final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setOrder(Integer.valueOf(2));
    templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
    templateResolver.setPrefix("/mail-templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setCharacterEncoding("UTF-8");
    templateResolver.setCacheable(false);
    return templateResolver;
  }
}
