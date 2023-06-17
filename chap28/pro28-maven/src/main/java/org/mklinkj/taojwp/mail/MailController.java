package org.mklinkj.taojwp.mail;

import static org.mklinkj.taojwp.common.vault.VaultClientHelper.vaultClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequestMapping("/mail")
@RequiredArgsConstructor
@Controller
public class MailController {
  private final MailService mailService;

  private final EmailTemplateService templateService;

  @GetMapping("/sendMail")
  public void sendMailForm() {}

  @PostMapping("/sendMail")
  public String sendSimpleMail(RedirectAttributes redirectAttributes) {
    Book book = new Book();
    book.setName("자바 웹을 다루는 기술");
    book.setDesc("JSP, 서블릿, 스프링까지 실무에서 알아야 할 기술은 따로 있다!");
    book.setLink("https://www.yes24.com/Product/Goods/68371015");

    try {
      mailService.sendMail(
          vaultClient().get("mail.owner.username"),
          "테스트 메일",
          templateService.bookEmailTemplate(book),
          true);
      mailService.sendPreConfiguredMail("테스트 메일 입니다. - PreConfiguredMail");
      redirectAttributes.addFlashAttribute("result", "메일을 보냈습니다.");
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      redirectAttributes.addFlashAttribute("result", e.getMessage());
    }
    return "redirect:sendMail";
  }
}
