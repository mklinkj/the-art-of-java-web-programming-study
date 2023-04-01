package org.mklinkj.taojwp.sec02.ex02;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;
import org.mklinkj.taojwp.sec01.ex01.MemberDAO;
import org.mklinkj.taojwp.sec01.ex01.MemberVO;

@Slf4j
@WebServlet("/member2/*")
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
    // Flash Attribute 검사 및 정리
    cleanFlashAttribute(request);

    LOGGER.info("### servletPath: {}", request.getServletPath());

    String action = request.getPathInfo();
    LOGGER.info("action: {}", action);

    String nextPage = null;

    if (action == null || action.equals("/listMembers.do")) {
      List<MemberVO> memberList = memberDAO.listMembers();
      request.setAttribute("memberList", memberList);
      nextPage = "/test03/listMembers.jsp";

    } else if (action.equals("/addMember.do")) {
      String id = request.getParameter("id");
      String pwd = request.getParameter("pwd");
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      MemberVO member = MemberVO.builder().id(id).pwd(pwd).name(name).email(email).build();
      memberDAO.addMember(member);

      setFlashAttribute(request, "msg", "registered");
      nextPage = String.format("redirect:%s/listMembers.do", request.getServletPath());

    } else if (action.equals("/memberForm.do")) {
      nextPage = "/test03/memberForm.jsp";
    } else if (action.equals("/modMemberForm.do")) {
      String id = request.getParameter("id");
      Optional<MemberVO> result = memberDAO.findMember(id);
      MemberVO memInfo = result.orElseThrow();
      request.setAttribute("memInfo", memInfo);
      nextPage = "/test03/modMemberForm.jsp";

    } else if (action.equals("/modMember.do")) {
      String id = request.getParameter("id");
      String pwd = request.getParameter("pwd");
      String name = request.getParameter("name");
      String email = request.getParameter("email");
      MemberVO memberVO = new MemberVO(id, pwd, name, email);

      memberDAO.modMember(memberVO);
      setFlashAttribute(request, "msg", "modified");
      nextPage = String.format("redirect:%s/listMembers.do", request.getServletPath());

    } else if (action.equals("/delMember.do")) {
      String id = request.getParameter("id");
      int deletedCount = memberDAO.delMember(id);
      if (deletedCount != 1) {
        setFlashAttribute(request, "msg", "no deleted");
      } else {
        setFlashAttribute(request, "msg", "deleted");
      }

      nextPage = String.format("redirect:%s/listMembers.do", request.getServletPath());
    }

    if (nextPage == null) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST);
    } else if (nextPage.startsWith("redirect:")) {
      String redirectUrl = getServletContext().getContextPath() + nextPage.replace("redirect:", "");
      LOGGER.info("redirectUrl: {}", redirectUrl);
      response.sendRedirect(redirectUrl);
    } else {
      RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
      dispatch.forward(request, response);
    }
  }
}
