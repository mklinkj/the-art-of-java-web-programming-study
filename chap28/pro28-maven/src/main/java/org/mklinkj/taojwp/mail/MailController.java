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

  @GetMapping("/sendMail")
  public void sendMailForm() {}

  @PostMapping("/sendMail")
  public String sendSimpleMail(RedirectAttributes redirectAttributes) {

    try {
      mailService.sendMail(vaultClient().get("mail.owner.username"), "테스트 메일", "안녕하세요 테스트 메일입니다.");
      mailService.sendPreConfiguredMail("테스트 메일 입니다. - PreConfiguredMail");
      redirectAttributes.addFlashAttribute("result", "메일을 보냈습니다.");
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      redirectAttributes.addFlashAttribute("result", e.getMessage());
    }
    return "redirect:sendMail";
  }
}
