package org.mklinkj.taojwp.sec03.ex01;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class JsonServlet3Test extends MockHttpServletTestSupport<JsonServlet3> {

  @Test
  void testDoHandle() throws Exception {
    runGivenWhenThen(
        () -> {},
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThatJson(response.getContentAsString())
              .and( //
                  a -> a.node("members[0].name").isEqualTo("박지성"), //
                  a -> a.node("members[0].age").isEqualTo(25),
                  a -> a.node("members[0].gender").isEqualTo("남자"),
                  a -> a.node("members[0].nickname").isEqualTo("날센돌이"),
                  a -> a.node("members[1].name").isEqualTo("김연아"), //
                  a -> a.node("members[1].age").isEqualTo(21),
                  a -> a.node("members[1].gender").isEqualTo("여자"),
                  a -> a.node("members[1].nickname").isEqualTo("칼치"),
                  a -> a.node("books[0].title").isEqualTo("스마일 1"), //
                  a -> a.node("books[0].writer").isEqualTo("정션링크1"),
                  a -> a.node("books[0].price").isEqualTo("30000"),
                  a -> a.node("books[0].genre").isEqualTo("IT"),
                  a -> a.node("books[0].image").isEqualTo("smile.png"),
                  a -> a.node("books[1].title").isEqualTo("스마일 2"), //
                  a -> a.node("books[1].writer").isEqualTo("정션링크2"),
                  a -> a.node("books[1].price").isEqualTo("12000"),
                  a -> a.node("books[1].genre").isEqualTo("IT"),
                  a -> a.node("books[1].image").isEqualTo("smile2.png"));
        });
  }

  @Override
  protected Class<JsonServlet3> getServletClass() {
    return JsonServlet3.class;
  }

  @Override
  protected String getServletPath() {
    return "/json3";
  }
}
