package org.mklinkj.taojwp.member;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.VIEW_ROOT_PATH_FORMAT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;

@Slf4j
@WebServlet("/member/*")
public class MemberController extends AbstractHttpServlet {
  static final String CURRENT_VIEW_PATH_FORMAT = VIEW_ROOT_PATH_FORMAT.formatted("/member%s");

  private MemberDAO memberDAO;

  @Override
  public void init() {
    super.init();
    memberDAO = applicationContext.getBean(MemberDAO.class);
  }

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
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
      nextPage = CURRENT_VIEW_PATH_FORMAT.formatted("/listMembers.jsp");

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
      nextPage = CURRENT_VIEW_PATH_FORMAT.formatted("/memberForm.jsp");
    } else if (action.equals("/modMemberForm.do")) {
      String id = request.getParameter("id");
      Optional<MemberVO> result = memberDAO.findMember(id);
      MemberVO memInfo = result.orElseThrow();
      request.setAttribute("memInfo", memInfo);
      nextPage = CURRENT_VIEW_PATH_FORMAT.formatted("/modMemberForm.jsp");

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

    forwardOrRedirect(request, response, nextPage);
  }
}
