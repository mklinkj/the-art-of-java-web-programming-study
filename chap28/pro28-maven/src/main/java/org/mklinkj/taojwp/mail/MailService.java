package org.mklinkj.taojwp.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailService {
  private final JavaMailSender mailSender;

  private final SimpleMailMessage preConfiguredMessage;

  @Async
  public void sendMail(String to, String subject, String body) throws MessagingException {
    MimeMessage message = mailSender.createMimeMessage();

    MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    // messageHelper.setCc("#####@naver.com");
    // messageHelper.setFrom("xxxxx@naver.com", "홍길동");
    messageHelper.setSubject(subject);
    messageHelper.setTo(to);
    messageHelper.setText(body);
    mailSender.send(message);
  }

  @Async
  public void sendPreConfiguredMail(String message) {
    SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
    mailMessage.setText(message);
    mailSender.send(mailMessage);
  }
}
