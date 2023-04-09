package org.mklinkj.taojwp.member;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.LOGIN_INFO_KEY_NAME;
import static org.mklinkj.taojwp.common.constant.Constants.VIEW_ROOT_PATH_FORMAT;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.board.exception.InvalidRequestException;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;

@Slf4j
@WebServlet("/login/*")
public class LoginController extends AbstractHttpServlet {

  static final String CURRENT_VIEW_PATH_FORMAT = VIEW_ROOT_PATH_FORMAT.formatted("/login%s");

  private MemberDAO memberDAO;

  @Override
  public void init() {
    super.init();
    memberDAO = applicationContext.getBean("memberDAO", MemberDAO.class);
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

    try {
      if (action == null || action.equals("/loginForm.do")) {
        nextPage = CURRENT_VIEW_PATH_FORMAT.formatted("/loginForm.jsp");

      } else if (action.equals("/login.do")) {

        String id = request.getParameter("id");
        String pwd = request.getParameter("pwd");

        Optional<MemberVO> result = memberDAO.findMemberWithPassword(id, pwd);
        MemberVO member =
            result.orElseThrow(
                () -> {
                  throw new InvalidRequestException(
                      ModalMessage.builder()
                          .title("🎃 로그인 실패 🎃")
                          .content("사용자 이름 또는 암호가 잘못되었습니다. 😂😂😂")
                          .build());
                });

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_INFO_KEY_NAME, member);

        nextPage = String.format("redirect:/board/listArticles.do");

      } else if (action.equals("/logout.do")) {
        HttpSession session = request.getSession();
        session.invalidate();
        nextPage = String.format("redirect:/board/listArticles.do");
      }

    } catch (InvalidRequestException ire) {
      setFlashAttribute(request, "msg", ire.getModalMessage());
      nextPage = String.format("redirect:%s/loginForm.do", request.getServletPath());

    } catch (Exception e) {
      LOGGER.info("서블릿 오류: {}", e.getMessage());
      setFlashAttribute(
          request,
          "msg",
          ModalMessage.builder()
              .title("😈 잘못된 요청 또는 내부 오류입니다. 👎👎👎")
              .content("목록으로 돌아갑니다.., 서버 로그도 확인해주세요 🙏🙏🙏")
              .build());
      nextPage = String.format("redirect:%s/loginForm.do", request.getServletPath());
    }

    forwardOrRedirect(request, response, nextPage);
  }
}
