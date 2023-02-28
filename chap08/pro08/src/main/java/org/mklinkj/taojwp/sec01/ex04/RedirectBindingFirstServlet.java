package org.mklinkj.taojwp.sec01.ex04;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.Constants.SERVER_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/** 8.4.1 HttpServletRequest를 이용한 redirect 포워딩 시 바인딩 */
@WebServlet("/redirectBindingFirst")
public class RedirectBindingFirstServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.setCharacterEncoding(SERVER_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    request.setAttribute("address", "서울시 성북구");
    response.sendRedirect("redirectBindingSecond");
  }
}
