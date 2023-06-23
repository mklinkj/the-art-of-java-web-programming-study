package org.mklinkj.taojwp.mail;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class EmailTemplateServiceTests {
  @Autowired private EmailTemplateService emailTemplateService;

  @Test
  void emailTemplate() {
    Book book = new Book();
    book.setName("자바 웹을 다루는 기술");
    book.setDesc("JSP, 서블릿, 스프링까지 실무에서 알아야 할 기술은 따로 있다!");
    book.setLink("https://www.yes24.com/Product/Goods/68371015");

    String content = emailTemplateService.bookEmailTemplate(book);

    assertThat(content) //
        .contains(book.getName())
        .contains(book.getDesc())
        .contains(book.getLink());

    LOGGER.debug("템플릿 결과\n{}", content);
  }
}
