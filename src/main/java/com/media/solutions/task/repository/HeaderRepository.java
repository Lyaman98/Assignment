package com.media.solutions.task.repository;

import com.media.solutions.task.domain.Header;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HeaderRepository extends MongoRepository<Header,String> {
}
