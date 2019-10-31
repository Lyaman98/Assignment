package com.media.solutions.task.service;

import com.media.solutions.task.domain.TransactionDetails;
import com.media.solutions.task.repository.TransactionDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailsService {

    private TransactionDetailsRepository transactionDetailsRepository;

    public TransactionDetailsService(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    public TransactionDetails save(TransactionDetails transactionDetails){
        return transactionDetailsRepository.save(transactionDetails);
    }
}
