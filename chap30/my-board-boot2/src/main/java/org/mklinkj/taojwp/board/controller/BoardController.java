package org.mklinkj.taojwp.board.controller;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.mklinkj.taojwp.board.constant.Constants.PAGE_NAVI_SIZE;
import static org.mklinkj.taojwp.board.constant.Constants.PAGE_SIZE;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.mklinkj.taojwp.board.domain.ArticleVO;
import org.mklinkj.taojwp.board.exception.InvalidRequestException;
import org.mklinkj.taojwp.board.service.BoardService;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;
import org.mklinkj.taojwp.file.AttachFile;
import org.mklinkj.taojwp.file.FileUploadService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
  private final String uploadTempPath = ProjectDataUtils.getProperty("upload_temp_path");

  private final String uploadPath = ProjectDataUtils.getProperty("upload_path");

  private final BoardService boardService;

  private final FileUploadService fileUploadService;

  @GetMapping("/listArticles.do")
  public String listArticles(
      @RequestParam(name = "section", defaultValue = "1") Integer section,
      @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
      Model model) {

    Map<String, Object> articlesMap =
        boardService.listArticles(Map.of("section", section, "pageNum", pageNum));
    articlesMap.put("section", section);
    articlesMap.put("pageNum", pageNum);
    articlesMap.put("pageSize", PAGE_SIZE);
    articlesMap.put("pageNaviSize", PAGE_NAVI_SIZE);
    articlesMap.put(
        "ceilTotArticles",
        (int) (Math.ceil((int) articlesMap.get("totArticles") / (double) PAGE_SIZE) * PAGE_SIZE));
    model.addAttribute("articlesMap", articlesMap);
    LOGGER.info("list page articlesMap: {}", articlesMap);

    return "board/listArticles";
  }

  @GetMapping("/articleForm.do")
  public void articleForm() {}

  @PostMapping("/addArticle.do")
  public String addArticle(
      ArticleVO articleVO,
      MultipartHttpServletRequest multipartHttpServletRequest,
      RedirectAttributes redirectAttributes)
      throws IOException {

    Authentication loginMember = SecurityContextHolder.getContext().getAuthentication();

    articleVO.setId(loginMember.getName());

    List<AttachFile> attachFileList =
        fileUploadService.uploadArticleAttachFile(multipartHttpServletRequest);

    if (!attachFileList.isEmpty()) {
      // TODO: 아직은 파일 1개만 첨부 가능
      AttachFile attachFile = attachFileList.get(0);
      articleVO.setImageFileName(attachFile.getOriginalFileName());
    }

    int articleNo = boardService.addArticle(articleVO);

    if (!attachFileList.isEmpty()) {
      // TODO: 아직은 파일 1개만 첨부 가능
      AttachFile attachFile = attachFileList.get(0);
      File srcFile = new File(uploadTempPath + File.separator + attachFile.getTempFileName());
      File destDir = new File(uploadPath + File.separator + articleNo);
      destDir.mkdirs();
      File destFile =
          new File(
              uploadPath
                  + File.separator
                  + articleNo
                  + File.separator
                  + attachFile.getOriginalFileName());
      Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
    }

    redirectAttributes.addFlashAttribute(
        "msg", ModalMessage.builder().title("🎊 등록 성공 🎊").content("새 게시글 등록에 성공하였습니다.🎉").build());

    return "redirect:listArticles.do";
  }

  @GetMapping("/viewArticle.do")
  public String viewArticle(Integer articleNo, Model model) {
    ArticleVO article = boardService.viewArticle(articleNo);
    if (article == null) {
      throw new NoSuchElementException("해당 게시물이 없습니다. 조회하려는 게시물번호: " + articleNo);
    }
    model.addAttribute("article", article);
    return "board/viewArticle";
  }

  @PostMapping("/modArticle.do")
  public String modArticle(
      ArticleVO articleVO,
      MultipartHttpServletRequest multipartHttpServletRequest,
      RedirectAttributes redirectAttributes)
      throws IOException {

    Authentication loginMember = SecurityContextHolder.getContext().getAuthentication();

    ArticleVO dbArticle = boardService.viewArticle(articleVO.getArticleNo());

    if (!dbArticle.getId().equals(loginMember.getName())) {
      LOGGER.error(
          "잘못된 수정 요청: 게시글 작성자 ID {}, 로그인 ID: {}", dbArticle.getId(), loginMember.getName());
      throw new InvalidRequestException(
          ModalMessage.builder().title("잘못된 수정 요청").content("자신의 글만 수정 가능합니다. 😅").build());
    }

    List<AttachFile> attachFileList =
        fileUploadService.uploadArticleAttachFile(multipartHttpServletRequest);

    if (!attachFileList.isEmpty()) {
      articleVO.setImageFileName(attachFileList.get(0).getOriginalFileName());
    }

    boardService.modArticle(articleVO);

    if (!attachFileList.isEmpty()) {
      AttachFile attachFile = attachFileList.get(0);

      File srcFile = new File(uploadTempPath + File.separator + attachFile.getTempFileName());
      File destDir = new File(uploadPath + File.separator + articleVO.getArticleNo());
      destDir.mkdirs();

      File destFile = new File(destDir, attachFile.getOriginalFileName());
      Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
    }

    redirectAttributes.addAttribute("articleNo", articleVO.getArticleNo());
    redirectAttributes.addFlashAttribute(
        "msg", ModalMessage.builder().title("🎊 수정 성공 🎊").content("게시글 수정에 성공하였습니다.🎉").build());

    return "redirect:viewArticle.do";
  }

  @PostMapping("/removeArticle.do")
  public String removeArticle(Integer articleNo, RedirectAttributes redirectAttributes)
      throws IOException {

    Authentication loginMember = SecurityContextHolder.getContext().getAuthentication();

    ArticleVO dbArticle = boardService.viewArticle(articleNo);

    if (!dbArticle.getId().equals(loginMember.getName())) {
      throw new InvalidRequestException(
          ModalMessage.builder().title("잘못된 삭제 요청").content("자신의 글만 삭제 가능합니다. 😅").build());
    }

    List<Integer> removedArticleNoList = boardService.removeArticle(articleNo);
    for (int removedArticleNo : removedArticleNoList) {
      File imageDir = new File(uploadPath + File.separator + removedArticleNo);
      FileUtils.deleteDirectory(imageDir);
    }

    redirectAttributes.addFlashAttribute(
        "msg", ModalMessage.builder().title("🎊 삭제 성공 🎊").content("게시글 삭제에 성공하였습니다.🎉").build());

    return "redirect:listArticles.do";
  }

  @PostMapping("/replyForm.do")
  public String replyForm(@RequestParam("parentNo") Integer parentNo, HttpSession session) {
    LOGGER.info("### replyForm.do parentNo: {}", parentNo);
    session.setAttribute("parentNo", parentNo);
    return "board/replyForm";
  }

  @PostMapping("/addReply.do")
  public String addReply(
      ArticleVO articleVO,
      HttpSession session,
      MultipartHttpServletRequest multipartHttpServletRequest,
      RedirectAttributes redirectAttributes)
      throws IOException {
    int parentNo = (Integer) session.getAttribute("parentNo");
    Authentication loginMember = SecurityContextHolder.getContext().getAuthentication();
    session.removeAttribute("parentNo");

    List<AttachFile> attachFileList =
        fileUploadService.uploadArticleAttachFile(multipartHttpServletRequest);

    if (!attachFileList.isEmpty()) {
      articleVO.setImageFileName(attachFileList.get(0).getOriginalFileName());
    }

    articleVO.setParentNo(parentNo);
    articleVO.setId(loginMember.getName());
    int articleNo = boardService.addReply(articleVO);

    if (!attachFileList.isEmpty()) {
      AttachFile attachFile = attachFileList.get(0);

      File srcFile = new File(uploadTempPath + File.separator + attachFile.getTempFileName());
      File destDir = new File(uploadPath + File.separator + articleNo);
      destDir.mkdirs();
      File destFile =
          new File(
              uploadPath
                  + File.separator
                  + articleNo
                  + File.separator
                  + attachFile.getOriginalFileName());
      Files.move(srcFile.toPath(), destFile.toPath(), REPLACE_EXISTING);
    }

    redirectAttributes.addAttribute("articleNo", articleNo);
    redirectAttributes.addFlashAttribute(
        "msg",
        ModalMessage.builder().title("🎊 답글 추가 성공 🎊").content("답글 게시글을 추가하였습니다.🎉").build());

    return "redirect:viewArticle.do";
  }

  @ExceptionHandler(InvalidRequestException.class)
  public String handleInvalidRequestException(
      InvalidRequestException ire, RedirectAttributes redirectAttributes) {
    LOGGER.error("잘못된 요청 오류: {}", ire.getMessage(), ire);
    redirectAttributes.addFlashAttribute("msg", ire.getModalMessage());
    return "redirect:listArticles.do";
  }

  @ExceptionHandler(Exception.class)
  public String handleException(Exception e, RedirectAttributes redirectAttributes) {
    LOGGER.error("공통 오류: {}", e.getMessage(), e);
    redirectAttributes.addFlashAttribute(
        "msg",
        ModalMessage.builder()
            .title("😈 잘못된 요청 또는 내부 오류입니다. 👎👎👎")
            .content("목록으로 돌아갑니다.., 서버 로그도 확인해주세요 🙏🙏🙏")
            .build());
    return "redirect:listArticles.do";
  }
}