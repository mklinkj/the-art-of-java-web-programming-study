package org.mklinkj.taojwp.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class EmailTemplateService {
  private final TemplateEngine emailTemplateEngine;

  @Autowired
  public EmailTemplateService(
      @Qualifier("emailTemplateEngine") TemplateEngine emailTemplateEngine) {
    this.emailTemplateEngine = emailTemplateEngine;
  }

  public String bookEmailTemplate(Book book) {
    Context ctx = new Context();
    ctx.setVariable("book", book);
    return emailTemplateEngine.process("html/book_mail.html", ctx);
  }
}
