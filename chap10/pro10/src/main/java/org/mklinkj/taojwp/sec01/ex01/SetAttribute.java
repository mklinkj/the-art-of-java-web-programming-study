package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/set")
public class SetAttribute extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);

    String contextMessage = "context에 바인딩 됩니다.";
    String sessionMessage = "session에 바인딩 됩니다.";
    String requestMessage = "request에 바인딩 됩니다.";

    ServletContext context = getServletContext();
    HttpSession session = request.getSession();

    context.setAttribute("context", contextMessage);
    session.setAttribute("session", sessionMessage);
    request.setAttribute("request", requestMessage);

    LOGGER.info("바인딩을 수행합니다.");
  }
}
