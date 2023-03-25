package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;

@Slf4j
@WebServlet("/ajaxTest1")
public class AjaxTest1 extends AbstractHttpServlet {

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    String param = request.getParameter("param");
    LOGGER.info("param = {}", param);

    PrintWriter pw = response.getWriter();
    pw.print("안녕하세요! 서버입니다.");
  }
}
