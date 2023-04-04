package org.mklinkj.taojwp.sec03.sec03.brd04;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.mklinkj.taojwp.common.constant.Constants.HTML_CONTENT_TYPE;
import static org.mklinkj.taojwp.common.constant.Constants.MEGA_BYTE;
import static org.mklinkj.taojwp.common.constant.Constants.UPLOAD_DIR;
import static org.mklinkj.taojwp.common.constant.Constants.UPLOAD_TEMP_DIR;
import static org.mklinkj.taojwp.common.constant.Constants.UTF_8_ENCODING;
import static org.mklinkj.taojwp.common.util.CommonUtils.fileNameOnly;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.mklinkj.taojwp.common.servlet.AbstractHttpServlet;
import org.mklinkj.taojwp.sec03.brd01.ArticleVO;
import org.mklinkj.taojwp.sec03.brd01.BoardService;

@Slf4j
@WebServlet("/board4/*")
@MultipartConfig(
    fileSizeThreshold = MEGA_BYTE,
    maxFileSize = 10 * MEGA_BYTE,
    maxRequestSize = 15 * MEGA_BYTE,
    location = UPLOAD_TEMP_DIR)
public class BoardController extends AbstractHttpServlet {
  private BoardService boardService;

  public void init() {
    this.boardService = new BoardService();
  }

  @Override
  protected void doHandle(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    cleanFlashAttribute(request);

    String nextPage;

    request.setCharacterEncoding(UTF_8_ENCODING);
    response.setContentType(HTML_CONTENT_TYPE);

    String action = request.getPathInfo();
    LOGGER.info("action: {}", action);

    try {

      List<ArticleVO> articleList;

      if (action == null || action.equals("/listArticles.do")) {
        articleList = boardService.listArticles();
        request.setAttribute("articleList", articleList);
        nextPage = "/board03/listArticles.jsp";

      } else if (action.equals("/articleForm.do")) {
        nextPage = "/board03/articleForm.jsp";

      } else if (action.equals("/addArticle.do")) {

        Map<String, String> articleMap = upload(request);
        String title = articleMap.get("title");
        String content = articleMap.get("content");
        String imageFileName = articleMap.get("imageFileName");

        ArticleVO articleVO =
            ArticleVO.builder() //
                .parentNo(0)
                .id("hong")
                .title(title)
                .content(content)
                .imageFileName(imageFileName)
                .build();

        int articleNo = boardService.addArticle(articleVO);

        if (imageFileName != null && !imageFileName.isBlank()) {
          File srcFile =
              new File(UPLOAD_TEMP_DIR + File.separator + File.separator + imageFileName);
          File destDir = new File(UPLOAD_DIR + File.separator + articleNo);
          destDir.mkdirs();
          File destFile =
              new File(UPLOAD_DIR + File.separator + articleNo + File.separator + imageFileName);
          Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
        }
        setFlashAttribute(
            request,
            "msg",
            ModalMessage.builder().title("🎊 등록 성공 🎊").content("새 게시글 등록에 성공하였습니다.🎉").build());
        nextPage = String.format("redirect:%s/listArticles.do", request.getServletPath());

      } else if (action.equals("/viewArticle.do")) {
        String articleNo = request.getParameter("articleNo");
        ArticleVO article = boardService.viewArticle(Integer.parseInt(articleNo));
        if (article == null) {
          throw new NoSuchElementException("해당 게시물이 없습니다. 조회하려는 게시물번호: " + articleNo);
        }
        request.setAttribute("article", article);
        nextPage = "/board03/viewArticle.jsp";

      } else if (action.equals("/modArticle.do")) {
        Map<String, String> articleMap = upload(request);
        int articleNo = Integer.parseInt(articleMap.get("articleNo"));
        String title = articleMap.get("title");
        String content = articleMap.get("content");
        String imageFileName = articleMap.get("imageFileName");

        String originalFileName = articleMap.get("originalFileName");

        ArticleVO articleVO =
            ArticleVO.builder() //
                .articleNo(articleNo)
                .title(title)
                .content(content)
                .imageFileName(imageFileName)
                .build();

        boardService.modArticle(articleVO);

        if (imageFileName != null && !imageFileName.isBlank()) {
          File srcFile =
              new File(UPLOAD_TEMP_DIR + File.separator + File.separator + imageFileName);
          File destDir = new File(UPLOAD_DIR + File.separator + articleNo);
          destDir.mkdirs();
          if (originalFileName != null && !originalFileName.isBlank()) {
            File originalFile = new File(destDir, originalFileName);
            originalFile.delete();
          }
          File destFile = new File(destDir, imageFileName);
          Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
        }

        setFlashAttribute(
            request,
            "msg",
            ModalMessage.builder().title("🎊 수정 성공 🎊").content("게시글 수정에 성공하였습니다.🎉").build());
        nextPage =
            String.format(
                "redirect:%s/viewArticle.do?articleNo=%s", request.getServletPath(), articleNo);

      } else if (action.equals("/removeArticle.do")) {
        Integer articleNo = Integer.parseInt(request.getParameter("articleNo"));

        List<Integer> removedArticleNoList = boardService.removeArticle(articleNo);
        for (int removedArticleNo : removedArticleNoList) {
          File imageDir = new File(UPLOAD_DIR + File.separator + removedArticleNo);
          FileUtils.deleteDirectory(imageDir);
        }

        setFlashAttribute(
            request,
            "msg",
            ModalMessage.builder().title("🎊 삭제 성공 🎊").content("게시글 삭제에 성공하였습니다.🎉").build());
        nextPage = String.format("redirect:%s/listArticles.do", request.getServletPath());

      } else if (action.equals("/replyForm.do")) {
        String pno = request.getParameter("parentNo");

        int parentNo = Integer.parseInt(pno);
        HttpSession session = request.getSession();
        session.setAttribute("parentNo", parentNo);
        nextPage = "/board03/replyForm.jsp";

      } else if (action.equals("/addReply.do")) {
        HttpSession session = request.getSession();
        int parentNo = (Integer) session.getAttribute("parentNo");
        session.removeAttribute("parentNo");

        Map<String, String> articleMap = upload(request);
        String title = articleMap.get("title");
        String content = articleMap.get("content");
        String imageFileName = articleMap.get("imageFileName");

        ArticleVO articleVO =
            ArticleVO.builder() //
                .parentNo(parentNo)
                .id("lee")
                .title(title)
                .content(content)
                .imageFileName(imageFileName)
                .build();

        int articleNo = boardService.addArticle(articleVO);

        if (imageFileName != null && !imageFileName.isBlank()) {
          File srcFile =
              new File(UPLOAD_TEMP_DIR + File.separator + File.separator + imageFileName);
          File destDir = new File(UPLOAD_DIR + File.separator + articleNo);
          destDir.mkdirs();
          File destFile =
              new File(UPLOAD_DIR + File.separator + articleNo + File.separator + imageFileName);
          Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
        }

        setFlashAttribute(
            request,
            "msg",
            ModalMessage.builder().title("🎊 답글 추가 성공 🎊").content("답글 게시글을 추가하였습니다.🎉").build());
        nextPage =
            String.format(
                "redirect:%s/viewArticle.do?articleNo=%s", request.getServletPath(), articleNo);
      } else {
        nextPage = null;
      }

      forwardOrRedirect(request, response, nextPage);
    } catch (Exception e) {
      LOGGER.info("서블릿 오류: {}", e.getMessage());
      setFlashAttribute(
          request,
          "msg",
          ModalMessage.builder()
              .title("😈 잘못된 요청 또는 내부 오류입니다. 👎👎👎")
              .content("목록으로 돌아갑니다.., 서버 로그도 확인해주세요 🙏🙏🙏")
              .build());
      nextPage = String.format("redirect:%s/listArticles.do", request.getServletPath());
      forwardOrRedirect(request, response, nextPage);
    }
  }

  private Map<String, String> upload(HttpServletRequest request)
      throws IOException, ServletException {

    Map<String, String> articleMap = new HashMap<>();

    Collection<Part> parts = request.getParts();
    for (Part p : parts) {
      if (isFormField(p)) {
        LOGGER.info("{}={}", p.getName(), request.getParameter(p.getName()));
        articleMap.put(p.getName(), request.getParameter(p.getName()));
      } else {
        LOGGER.info("매개변수 이름: {}", p.getName());
        LOGGER.info("파일 이름: {}", p.getSubmittedFileName());
        LOGGER.info("파일 크기: {}", p.getSize());

        articleMap.put(p.getName(), p.getSubmittedFileName());
        p.write(fileNameOnly(p.getSubmittedFileName()));
      }
    }

    return articleMap;
  }

  private boolean isFormField(Part part) {
    return part.getSubmittedFileName() == null || part.getSubmittedFileName().isBlank();
  }
}
