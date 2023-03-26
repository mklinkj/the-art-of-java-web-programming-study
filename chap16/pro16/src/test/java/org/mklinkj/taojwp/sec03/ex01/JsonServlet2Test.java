package org.mklinkj.taojwp.sec03.ex01;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

@Slf4j
class JsonServlet2Test extends MockHttpServletTestSupport<JsonServlet2> {

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
                  a -> a.node("members[1].nickname").isEqualTo("칼치"));
        });
  }

  @Override
  protected Class<JsonServlet2> getServletClass() {
    return JsonServlet2.class;
  }

  @Override
  protected String getServletPath() {
    return "/json2";
  }
}
