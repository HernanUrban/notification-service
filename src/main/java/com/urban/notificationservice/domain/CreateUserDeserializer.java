package com.urban.notificationservice.domain;

import org.springframework.kafka.support.serializer.JsonDeserializer;

public class CreateUserDeserializer extends JsonDeserializer<CreateUserEvent> {

  public CreateUserDeserializer() {
    super(CreateUserEvent.class);
  }

}
