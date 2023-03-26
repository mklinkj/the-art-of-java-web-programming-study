package org.mklinkj.taojwp.sec03.ex01;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.http.HttpStatus;

class JsonServlet1Test extends MockHttpServletTestSupport<JsonServlet1> {

  @Test
  void testDoHandle() throws Exception {
    runGivenWhenThen(
        () ->
            request.setParameter(
                "jsonInfo",
                "{\"name\":\"박지성\",\"age\":25,\"gender\":\"남자\",\"nickname\":\"날센돌이\"}"), //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThatJson(response.getContentAsString())
              .and( //
                  a -> a.node("name").isEqualTo("박지성"), //
                  a -> a.node("age").isEqualTo(25),
                  a -> a.node("gender").isEqualTo("남자"),
                  a -> a.node("nickname").isEqualTo("날센돌이"));
        });
  }

  @Override
  protected Class<JsonServlet1> getServletClass() {
    return JsonServlet1.class;
  }

  @Override
  protected String getServletPath() {
    return "/json";
  }
}
