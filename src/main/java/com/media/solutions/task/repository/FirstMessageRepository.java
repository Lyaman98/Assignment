package com.media.solutions.task.repository;

import com.media.solutions.task.domain.FirstMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FirstMessageRepository extends MongoRepository<FirstMessage,String> {
}
