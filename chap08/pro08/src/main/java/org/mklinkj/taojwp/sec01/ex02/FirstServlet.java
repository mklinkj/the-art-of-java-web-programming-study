package org.mklinkj.taojwp.sec01.ex02;

import static org.mklinkj.taojwp.common.Constants.HTML_CONTENT_TYPE;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/first2")
public class FirstServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    response.setContentType(HTML_CONTENT_TYPE);
    response.addHeader("Refresh", "1;url=second?forwardingType=refresh");
  }
}
