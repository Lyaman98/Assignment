package com.media.solutions.task.repository;

import com.media.solutions.task.domain.SecondMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SecondMessageRepository extends MongoRepository<SecondMessage,String> {
}
