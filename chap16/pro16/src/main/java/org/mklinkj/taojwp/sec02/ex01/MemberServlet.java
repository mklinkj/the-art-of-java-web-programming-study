package org.mklinkj.taojwp.sec02.ex01;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;
import org.mklinkj.taojwp.common.util.DBUtils;

@Slf4j
@WebServlet("/mem")
public class MemberServlet extends AbstractHttpServlet {
  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    // 서블릿의 기능과는 관계없지만... 계속 데이터소스를 계속 불러와서 동일한 데이터소스를 반환하는지 궁금해서...
    LOGGER.info(
        "## DataSource가 동일함 {} ##",
        DBUtils.getDataSourceFromJNDI() == DBUtils.getDataSourceFromJNDI());

    PrintWriter writer = response.getWriter();
    String id = request.getParameter("id");

    LOGGER.info("id={}", id);
    MemberDAO memberDAO = new MemberDAO();
    boolean overlappedId = memberDAO.overlappedId(id);

    if (overlappedId == true) {
      writer.print("not_usable");
    } else {
      writer.print("usable");
    }
  }
}
