package org.mklinkj.taojwp.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import java.io.StringWriter;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 스프링의 View로 사용할 필요는 없어보여서, 아래 가이드 보고 템플릿과 데이터 조합해서 String 얻는 메서드 하나만 만들어봤다. <a
 * href="https://freemarker.apache.org/docs/pgui_quickstart.html">개발자 가이드 - 시작하기</a>
 */
@RequiredArgsConstructor
@Service
public class EmailTemplateService {

  private final Configuration freemarkerCfg;

  public String bookEmailTemplate(Book book) {
    try {
      Template template = freemarkerCfg.getTemplate("book_mail.ftl");
      StringWriter out = new StringWriter();
      template.process(Map.of("book", book), out);
      return out.toString();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    }
  }
}
