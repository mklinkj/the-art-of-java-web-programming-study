package org.mklinkj.taojwp.mail;

import static org.mklinkj.taojwp.common.vault.VaultClientHelper.vaultClient;

import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Slf4j
@Configuration
public class JavaMailConfig {

  @Bean
  public JavaMailSender mailSender() {

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(465);
    mailSender.setUsername(vaultClient().get("mail.owner.username"));
    mailSender.setPassword(vaultClient().get("mail.owner.password"));

    Properties props = new Properties();
    props.put("mail.transport.protocol", "smtp");
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.debug", true);

    mailSender.setJavaMailProperties(props);

    return mailSender;
  }

  @Bean
  public SimpleMailMessage preConfiguredMessage() {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

    simpleMailMessage.setTo(vaultClient().get("mail.address.customer"));
    simpleMailMessage.setFrom(vaultClient().get("mail.address.owner"));
    simpleMailMessage.setSubject("테스트 메일입니다.");

    return simpleMailMessage;
  }
}
