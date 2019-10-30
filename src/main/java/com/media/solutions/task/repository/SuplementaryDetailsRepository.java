package com.media.solutions.task.repository;


import com.media.solutions.task.domain.SuplementaryDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SuplementaryDetailsRepository extends MongoRepository<SuplementaryDetails,String> {
}
