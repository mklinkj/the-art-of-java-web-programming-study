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
@WebServlet("/ajaxTest2")
public class AjaxTest2 extends AbstractHttpServlet {

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    PrintWriter pw = response.getWriter();
    pw.print(
        """
    <main>
      <book>
        <title><![CDATA[스마일1 책]]></title>
        <writer><![CDATA[스마일1 저자]]></writer>
        <image><![CDATA[http://localhost:8090/pro16/image/smile.png]]></image>
      </book>
      <book>
        <title><![CDATA[스마일2 책]]></title>
        <writer><![CDATA[스마일2 저자]]></writer>
        <image><![CDATA[http://localhost:8090/pro16/image/smile2.png]]></image>
      </book>
    </main>
    """);
  }
}
