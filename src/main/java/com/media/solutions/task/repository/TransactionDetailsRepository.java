package com.media.solutions.task.repository;

import com.media.solutions.task.domain.TransactionDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionDetailsRepository extends MongoRepository<TransactionDetails,String> {
}
