package org.mklinkj.taojwp.sec03.ex01;

import static org.mklinkj.taojwp.common.constant.Constants.JSON_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;
import static org.mklinkj.taojwp.common.util.ObjectMapperHelper.objectMapper;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;
import org.mklinkj.taojwp.sec03.ex01.SportPlayer.SportPlayerBuilder;

@Slf4j
@WebServlet("/json2")
public class JsonServlet2 extends AbstractHttpServlet {

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(JSON_CONTENT_TYPE);

    Map<String, List<SportPlayer>> jsonInfo =
        Map.of(
            "members",
            List.of(
                SportPlayer.builder().name("박지성").age(25).gender("남자").nickname("날센돌이").build(),
                SportPlayer.builder().name("김연아").age(21).gender("여자").nickname("칼치").build()));
    response
        .getWriter() //
        .print(objectMapper().writeValueAsString(jsonInfo));
  }
}
