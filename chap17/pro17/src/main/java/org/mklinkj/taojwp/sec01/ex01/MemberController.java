package org.mklinkj.taojwp.sec01.ex01;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;

@Slf4j
@WebServlet("/mem")
public class MemberController extends AbstractHttpServlet {

  private MemberDAO memberDAO;

  @Override
  public void init() {
    memberDAO = new MemberDAO();
  }

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    List<MemberVO> memberList = memberDAO.listMembers();

    request.setAttribute("memberList", memberList);

    RequestDispatcher dispatch = request.getRequestDispatcher("/test01/listMembers.jsp");
    dispatch.forward(request, response);
  }
}
