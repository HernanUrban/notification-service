package com.urban.notificationservice.service;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private MailContentBuilder mailContentBuilder;

  public void prepareAndSend(String recipient, String subject, String template, Map<String, String>
      variables) {
    MimeMessagePreparator messagePreparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom("hernan@urban-ms-ecosystem.com");
      messageHelper.setTo(recipient);
      messageHelper.setSubject(subject);
      String content = mailContentBuilder.build(template, variables);
      messageHelper.setText(content, true);
    };
    try {
      mailSender.send(messagePreparator);
    } catch (MailException e) {
      LOGGER.error("Unable to send the email", e);
    }
  }

}
