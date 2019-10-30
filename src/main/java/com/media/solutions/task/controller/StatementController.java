package com.media.solutions.task.controller;

import com.media.solutions.task.domain.*;
import com.media.solutions.task.repository.*;
import com.media.solutions.task.util.FileParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

@RestController
public class StatementController {

    private HeaderRepository headerRepository;
    private StatementRepository statementRepository;
    private TransactionDetailsRepository transactionDetailsRepository;
    private SuplementaryDetailsRepository suplementaryDetailsRepository;
    private TransactionRepository transactionRepository;

    private final Logger log = LoggerFactory.getLogger(StatementController.class);

    public StatementController(HeaderRepository headerRepository, StatementRepository statementRepository,
                               TransactionDetailsRepository transactionDetailsRepository, SuplementaryDetailsRepository suplementaryDetailsRepository, TransactionRepository transactionRepository) {
        this.headerRepository = headerRepository;
        this.statementRepository = statementRepository;
        this.transactionDetailsRepository = transactionDetailsRepository;

        this.suplementaryDetailsRepository = suplementaryDetailsRepository;
        this.transactionRepository = transactionRepository;
    }

    @GetMapping("/api")
    public void check(@RequestParam("path") String path) {

        FileParser fileParser = new FileParser();
        File file = new File(path);

        List<Statement> statements = fileParser.getFile(file);

        statements.forEach(statement -> {

            headerRepository.save(statement.getHeader());
            statement.getTransactions().forEach(transaction -> {
                transactionDetailsRepository.save(transaction.getTransactionDetails());
                if (transaction.getSuplementaryDetails() != null)
                suplementaryDetailsRepository.save(transaction.getSuplementaryDetails());
                transactionRepository.save(transaction);

            });
            statementRepository.save(statement);

        });
    }

}
