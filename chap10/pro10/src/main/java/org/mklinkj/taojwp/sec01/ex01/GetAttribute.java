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
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet("/get")
public class GetAttribute extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType(HTML_CONTENT_TYPE);

    PrintWriter out = response.getWriter();
    ServletContext context = getServletContext();
    HttpSession session = request.getSession();

    String contextMessage = (String) context.getAttribute("context");
    String sessionMessage = (String) session.getAttribute("session");
    String requestMessage = (String) request.getAttribute("request");

    out.printf("""
        context 값: %s<br>
        session 값: %s<br>
        request 값: %s<br>
        """, contextMessage, sessionMessage, requestMessage
    );

    LOGGER.info("바인딩을 수행합니다.");
  }
}
