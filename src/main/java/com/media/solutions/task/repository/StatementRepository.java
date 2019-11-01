package com.media.solutions.task.repository;

import com.media.solutions.task.domain.Statement;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatementRepository extends MongoRepository<Statement, String> {
}
