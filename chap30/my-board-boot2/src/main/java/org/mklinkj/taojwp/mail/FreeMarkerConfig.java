package org.mklinkj.taojwp.mail;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import java.util.TimeZone;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {
  @Bean
  Configuration freemarkerCfg() {
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
    cfg.setClassForTemplateLoading(this.getClass(), "/freemarker");

    // 새로운 프로젝트에 추천 설정
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);
    cfg.setWrapUncheckedExceptions(true);
    cfg.setFallbackOnNullLoopVariable(false);
    cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
    return cfg;
  }
}
