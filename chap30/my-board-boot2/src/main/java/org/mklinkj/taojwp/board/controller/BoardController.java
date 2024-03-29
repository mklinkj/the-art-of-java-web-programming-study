package org.mklinkj.taojwp.board.controller;

import static org.mklinkj.taojwp.board.constant.Constants.PAGE_NAVI_SIZE;
import static org.mklinkj.taojwp.board.constant.Constants.PAGE_SIZE;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mklinkj.taojwp.board.domain.ArticleVO;
import org.mklinkj.taojwp.board.exception.InvalidRequestException;
import org.mklinkj.taojwp.board.service.BoardService;
import org.mklinkj.taojwp.common.domain.ModalMessage;
import org.mklinkj.taojwp.file.domain.AttachFile;
import org.mklinkj.taojwp.file.service.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board")
@Controller
public class BoardController {
  private final BoardService boardService;

  private final FileService fileService;

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
      @RequestPart("imageFile") List<MultipartFile> fileList,
      RedirectAttributes redirectAttributes)
      throws IOException {

    Authentication loginMember = SecurityContextHolder.getContext().getAuthentication();

    articleVO.setId(loginMember.getName());

    int articleNo = boardService.addArticle(articleVO);

    if (fileList != null) {
      fileService.uploadArticleAttachFile(fileList, articleNo);
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
    List<AttachFile> attachFileList = fileService.getAttachFileList(articleNo);
    model.addAttribute("attachFileList", attachFileList);

    return "board/viewArticle";
  }

  @PostMapping("/modArticle.do")
  public String modArticle(
      ArticleVO articleVO,
      @RequestParam(value = "uuidsToDelete", required = false) List<String> uuidsToDelete,
      @RequestPart(value = "imageFile", required = false) List<MultipartFile> fileList,
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

    boardService.modArticle(articleVO);

    if (fileList != null) {
      fileService.uploadArticleAttachFile(fileList, articleVO.getArticleNo());
    }
    // 수정시 기존 파일 삭제 체크가 설정 되었을 때, 정보 및 파일 삭제.
    if (uuidsToDelete != null && !uuidsToDelete.isEmpty()) {
      fileService.removeAttachFileByUuid(uuidsToDelete, articleVO.getArticleNo());
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
    fileService.removeAttachFile(removedArticleNoList);

    redirectAttributes.addFlashAttribute(
        "msg", ModalMessage.builder().title("🎊 삭제 성공 🎊").content("게시글 삭제에 성공하였습니다.🎉").build());

    return "redirect:listArticles.do";
  }

  @PostMapping("/replyForm.do")
  public String replyForm(@RequestParam("parentNo") Integer parentNo, HttpSession session) {
    LOGGER.info("### replyForm.do parentNo: {}", parentNo);
    // TODO: 부모글 게시물 번호를  Session 을 통해 전달할까?
    // Model에다  부모글 게시물 번호를 설정하고 페이지에서 그 값을 다시 폼 전송하면 될텐데? 세션의 장점이 있는지? 딱히 없어보임 😅
    session.setAttribute("parentNo", parentNo);
    return "board/replyForm";
  }

  @PostMapping("/addReply.do")
  public String addReply(
      ArticleVO articleVO,
      HttpSession session,
      @RequestPart("imageFile") List<MultipartFile> fileList,
      RedirectAttributes redirectAttributes)
      throws IOException {
    int parentNo = (Integer) session.getAttribute("parentNo");
    Authentication loginMember = SecurityContextHolder.getContext().getAuthentication();
    session.removeAttribute("parentNo");

    articleVO.setParentNo(parentNo);
    articleVO.setId(loginMember.getName());
    int articleNo = boardService.addReply(articleVO);

    if (fileList != null) {
      fileService.uploadArticleAttachFile(fileList, articleVO.getArticleNo());
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
