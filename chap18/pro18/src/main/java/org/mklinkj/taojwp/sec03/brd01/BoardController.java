package org.mklinkj.taojwp.sec03.brd01;

import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;
import org.mklinkj.taojwp.sec01.ex01.MemberDAO;

@Slf4j
@WebServlet("/board/*")
public class BoardController extends AbstractHttpServlet {
  private  BoardService boardService;

  @Override
  public void init() {
    super.init();
    boardService = applicationContext.getBean("boardService", BoardService.class);
  }

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    cleanFlashAttribute(request);
    String nextPage = "/board01/listArticles.jsp";

    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    String action = request.getPathInfo();
    LOGGER.info("action: {}", action);

    List<ArticleVO> articleList;

    if (action == null || action.equals("/listArticles.do")) {
      articleList = boardService.listArticles();
      request.setAttribute("articleList", articleList);
      nextPage = "/board01/listArticles.jsp";
    } else if (action.equals("/viewArticle.do")) {
      setFlashAttribute(
          request, "msg", ModalMessage.builder().title("✨알림✨").content("조회 기능이 없습니다. 😅").build());
      nextPage = String.format("redirect:%s/listArticles.do", request.getServletPath());
    }

    forwardOrRedirect(request, response, nextPage);
  }
}
