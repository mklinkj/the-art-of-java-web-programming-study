package org.mklinkj.taojwp.board.filter;

import static org.mklinkj.taojwp.common.constant.Constants.LOGIN_INFO_KEY_NAME;
import static org.mklinkj.taojwp.common.servlet.FlashAttributeUtil.setFlashAttribute;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
public class BoardLoginCheckFilter extends OncePerRequestFilter {

  private final Set<String> requiredLoginPaths =
      Set.of(
          "/articleForm.do",
          "/addArticle.do",
          "/modArticle.do",
          "/removeArticle.do",
          "/replyForm.do",
          "/addReply.do",
          "/download.do");

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String pathInfo = request.getPathInfo();
    LOGGER.info("접근 경로: {}", pathInfo);
    if (requiredLoginPaths.contains(pathInfo)) {
      HttpSession session = request.getSession();

      if (session.getAttribute(LOGIN_INFO_KEY_NAME) == null) {
        setFlashAttribute(
            request,
            "msg", //
            ModalMessage.builder()
                .title("로그인 필요 😥") //
                .content("로그인 후, 사용가능합니다. 😛")
                .build());
        response.setStatus(HttpServletResponse.SC_FOUND);
        response.sendRedirect(request.getContextPath() + "/login/loginForm.do");
        return;
      }
    }

    filterChain.doFilter(request, response);
  }
}
