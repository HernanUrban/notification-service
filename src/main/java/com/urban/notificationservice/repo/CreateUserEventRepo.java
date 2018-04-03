package com.urban.notificationservice.repo;

import com.urban.notificationservice.domain.CreateUserEvent;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CreateUserEventRepo extends MongoRepository<CreateUserEvent, String> {

  List<CreateUserEvent> findByUserId(Long userId);
  List<CreateUserEvent> findByEmail(String email);

}