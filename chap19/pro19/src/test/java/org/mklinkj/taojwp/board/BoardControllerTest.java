package org.mklinkj.taojwp.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mklinkj.taojwp.board.BoardController.CURRENT_VIEW_PATH_FORMAT;
import static org.mklinkj.taojwp.common.constant.Constants.LOGIN_INFO_KEY_NAME;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.mklinkj.taojwp.member.MemberDAO;
import org.mklinkj.taojwp.test.support.MockHttpServletTestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockPart;

class BoardControllerTest extends MockHttpServletTestSupport<BoardController> {

  @Autowired MemberDAO memberDAO;

  @Test
  void testDoHandle_list() throws Exception {
    dbDataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setParameter("section", "2");
          request.setParameter("pageNum", "1");
        }, //
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          Map<String, Object> articlesMap =
              (Map<String, Object>) request.getAttribute("articlesMap");
          List<ArticleVO> articlesList = (List<ArticleVO>) articlesMap.get("articlesList");
          assertThat(articlesList).isNotEmpty();
          assertThat(response.getForwardedUrl())
              .isEqualTo(CURRENT_VIEW_PATH_FORMAT.formatted("/listArticles.jsp"));
        });
  }

  @Test
  void testDoHandle_articleForm() throws Exception {
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setPathInfo("/articleForm.do");
        },
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(response.getForwardedUrl())
              .isEqualTo(CURRENT_VIEW_PATH_FORMAT.formatted("/articleForm.jsp"));
        });
  }

  @Test
  void testDoHandle_addArticle() throws Exception {
    dbDataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          servlet.init();
          request.setPathInfo("/addArticle.do");

          request.setParameter("title", "제목");
          request.setParameter("content", "내용");
          request.addPart(new MockPart("title", null));
          request.addPart(new MockPart("content", null));
          // 아래와 같은 식으로 테스트가 불가능하다.
          // MockPart의
          /*
          request.addPart(
              new MockPart(
                  "imageFileName", "이미지파일.txt", "이미지파일_흉내".getBytes(StandardCharsets.UTF_8)));

          */
        },
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(response.getRedirectedUrl()).isEqualTo(getServletPath() + "/listArticles.do");
        });
  }

  @Test
  void testDoHandle_modArticle() throws Exception {
    dbDataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          request
              .getSession()
              .setAttribute(LOGIN_INFO_KEY_NAME, memberDAO.findMember("hong").orElseThrow());
          servlet.init();
          request.setPathInfo("/modArticle.do");

          request.setParameter("articleNo", "1");
          request.setParameter("title", "제목_수정");
          request.setParameter("content", "내용_수정");
          request.addPart(new MockPart("articleNo", null));
          request.addPart(new MockPart("title", null));
          request.addPart(new MockPart("content", null));
          // 아래와 같은 식으로 테스트가 불가능하다.
          // MockPart의
          /*
          request.addPart(
              new MockPart(
                  "imageFileName", "이미지파일.txt", "이미지파일_흉내".getBytes(StandardCharsets.UTF_8)));

          */
        },
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(request.getSession().getAttribute("msg"))
              .isEqualTo(
                  ModalMessage.builder()
                      .title("🎊 수정 성공 🎊")
                      .content("게시글 수정에 성공하였습니다.🎉")
                      .build());
          assertThat(response.getRedirectedUrl())
              .isEqualTo(getServletPath() + "/viewArticle.do?articleNo=1");
        });
  }

  @Test
  void testDoHandle_removeArticle() throws Exception {
    dbDataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          request
              .getSession()
              .setAttribute(LOGIN_INFO_KEY_NAME, memberDAO.findMember("hong").orElseThrow());
          request.setPathInfo("/removeArticle.do");
          request.setParameter("articleNo", "2");
          servlet.init();
        },
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(request.getSession().getAttribute("msg"))
              .isEqualTo(
                  ModalMessage.builder()
                      .title("🎊 삭제 성공 🎊")
                      .content("게시글 삭제에 성공하였습니다.🎉")
                      .build());
          assertThat(response.getRedirectedUrl()).isEqualTo(getServletPath() + "/listArticles.do");
        });
  }

  @Test
  void testDoHandle_replyForm() throws Exception {
    runGivenWhenThen(
        () -> {
          request.setPathInfo("/replyForm.do");
          request.setParameter("parentNo", "1");
          servlet.init();
        },
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
          assertThat(request.getSession().getAttribute("parentNo")).isEqualTo(1);
          assertThat(response.getForwardedUrl())
              .isEqualTo(CURRENT_VIEW_PATH_FORMAT.formatted("/replyForm.jsp"));
        });
  }

  @Test
  void testDoHandle_addReply() throws Exception {
    dbDataInitializer.resetDB();
    runGivenWhenThen(
        () -> {
          request
              .getSession()
              .setAttribute(LOGIN_INFO_KEY_NAME, memberDAO.findMember("mklinkj").orElseThrow());
          servlet.init();
          request.setPathInfo("/addReply.do");

          request.getSession().setAttribute("parentNo", 1);

          request.setParameter("title", "답글 제목");
          request.setParameter("content", "답글 내용");
          request.addPart(new MockPart("title", null));
          request.addPart(new MockPart("content", null));
          // 아래와 같은 식으로 테스트가 불가능하다.
          // MockPart의
          /*
          request.addPart(
              new MockPart(
                  "imageFileName", "이미지파일.txt", "이미지파일_흉내".getBytes(StandardCharsets.UTF_8)));

          */
        },
        () -> servlet.doHandle(request, response), //
        () -> {
          assertThat(response.getStatus()).isEqualTo(HttpStatus.FOUND.value());
          assertThat(request.getSession().getAttribute("msg"))
              .isEqualTo(
                  ModalMessage.builder()
                      .title("🎊 답글 추가 성공 🎊")
                      .content("답글 게시글을 추가하였습니다.🎉")
                      .build());
          assertThat(response.getRedirectedUrl())
              .containsPattern(getServletPath() + "/viewArticle.do\\?articleNo=\\d+");
        });
  }

  @Override
  protected Class<BoardController> getServletClass() {
    return BoardController.class;
  }

  @Override
  protected String getServletPath() {
    return "/board";
  }
}
