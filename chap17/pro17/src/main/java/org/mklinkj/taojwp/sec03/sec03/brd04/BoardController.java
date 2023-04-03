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
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
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
        File srcFile = new File(UPLOAD_TEMP_DIR + File.separator + File.separator + imageFileName);
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
      ArticleVO articleVO = boardService.viewArticle(Integer.parseInt(articleNo));
      request.setAttribute("article", articleVO);
      nextPage = "/board03/viewArticle.jsp";

    } else {
      nextPage = null;
    }

    forwardOrRedirect(request, response, nextPage);
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
