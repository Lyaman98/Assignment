package com.media.solutions.task.repository;


import com.media.solutions.task.domain.SupplementaryDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SupplementaryDetailsRepository extends MongoRepository<SupplementaryDetails,String> {
}
