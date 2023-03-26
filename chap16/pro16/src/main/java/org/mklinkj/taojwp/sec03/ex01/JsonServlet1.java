package org.mklinkj.taojwp.sec03.ex01;

import static org.mklinkj.taojwp.common.constant.Constants.JSON_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;
import static org.mklinkj.taojwp.common.util.ObjectMapperHelper.objectMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;

@Slf4j
@WebServlet("/json")
public class JsonServlet1 extends AbstractHttpServlet {

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(JSON_CONTENT_TYPE);

    String jsonInfo = request.getParameter("jsonInfo");

    request
        .getParameterMap()
        .forEach((k, v) -> LOGGER.info("key: {} / value: {}", k, Arrays.toString(v)));

    Map<String, Object> objectMap = objectMapper().readValue(jsonInfo, new TypeReference<>() {});

    LOGGER.info("* 회원 정보 *");
    LOGGER.info("이름: {}", objectMap.get("name"));
    LOGGER.info("나이: {}", objectMap.get("age"));
    LOGGER.info("성별: {}", objectMap.get("gender"));
    LOGGER.info("별명: {}", objectMap.get("nickname"));

    response
        .getWriter() //
        .print(objectMapper().writeValueAsString(objectMap));
  }
}
