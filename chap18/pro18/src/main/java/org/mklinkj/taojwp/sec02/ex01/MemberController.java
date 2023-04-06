package org.mklinkj.taojwp.sec02.ex01;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;
import org.mklinkj.taojwp.sec01.ex01.MemberDAO;
import org.mklinkj.taojwp.sec01.ex01.MemberVO;

@Slf4j
@WebServlet("/member1/*")
public class MemberController extends AbstractHttpServlet {

  private MemberDAO memberDAO;

  @Override
  public void init() {
    super.init();
    memberDAO = applicationContext.getBean("memberDAO", MemberDAO.class);
  }


  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    LOGGER.info("### servletPath: {}", request.getServletPath());

    String action = request.getPathInfo();
    LOGGER.info("action: {}", action);

    String nextPage = null;

    if (action == null || action.equals("/listMembers.do")) {
      List<MemberVO> memberList = memberDAO.listMembers();
      request.setAttribute("memberList", memberList);
      nextPage = "/test02/listMembers.jsp";

    } else if (action.equals("/addMember.do")) {
      String id = request.getParameter("id");
      String pwd = request.getParameter("pwd");
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      MemberVO member = MemberVO.builder().id(id).pwd(pwd).name(name).email(email).build();
      memberDAO.addMember(member);
      nextPage = String.format("redirect:%s/listMembers.do", request.getServletPath());

    } else if (action.equals("/memberForm.do")) {
      nextPage = "/test02/memberForm.jsp";
    }

    forwardOrRedirect(request, response, nextPage);
  }
}
