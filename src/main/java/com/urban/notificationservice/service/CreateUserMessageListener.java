package com.urban.notificationservice.service;

import com.urban.notificationservice.domain.CreateUserEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CreateUserMessageListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserMessageListener.class);

  private CountDownLatch latch = new CountDownLatch(1);

  @Autowired
  MailService mailService;

  public CountDownLatch getLatch() {
    return latch;
  }

  @KafkaListener(topics = "${kafka.consumer.topic}")
  public void receive(CreateUserEvent event) {
    LOGGER.info("received payload='{}'", event.toString());
    Map<String, String> var = new HashMap<>();
    var.put("first_name", event.getFirstName());
    var.put("action_url", "http://localhost:8080");
    mailService.prepareAndSend(event.getEmail(), "Welcome","verifyEmailTemplate", var);
    latch.countDown();
  }

}
