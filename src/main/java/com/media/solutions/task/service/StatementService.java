package com.media.solutions.task.service;

import com.media.solutions.task.domain.Statement;
import com.media.solutions.task.repository.*;
import com.media.solutions.task.util.FileParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public class StatementService {


    private HeaderService headerService;
    private TransactionService transactionService;
    private TransactionDetailsService transactionDetailsService;
    private SupplementaryDetailsService supplementaryDetailsService;
    private FirstMessageService firstMessageService;
    private SecondMessageService secondMessageService;
    private StatementRepository statementRepository;

    public StatementService(HeaderService headerService, TransactionService transactionService,
                            TransactionDetailsService transactionDetailsService, SupplementaryDetailsService supplementaryDetailsService,
                            FirstMessageService firstMessageService, SecondMessageService secondMessageService, StatementRepository statementRepository) {
        this.headerService = headerService;
        this.transactionService = transactionService;
        this.transactionDetailsService = transactionDetailsService;
        this.supplementaryDetailsService = supplementaryDetailsService;
        this.firstMessageService = firstMessageService;
        this.secondMessageService = secondMessageService;
        this.statementRepository = statementRepository;
    }

    public List<Statement> save(MultipartFile file) throws FileNotFoundException {

        FileParser fileParser = new FileParser();
        List<Statement> statements = fileParser.getFile(file);

        statements.forEach(statement -> {

            //before saving statement object we should save all other objects that are inside of it
            headerService.save(statement.getHeader());
            statement.getTransactions().forEach(transaction -> {
                transactionDetailsService.save(transaction.getTransactionDetails());
                if (transaction.getSupplementaryDetails() != null)
                    supplementaryDetailsService.save(transaction.getSupplementaryDetails());
                if (transaction.getFirstMessage() != null)
                    firstMessageService.save(transaction.getFirstMessage());
                if (transaction.getSecondMessage() != null)
                    secondMessageService.save(transaction.getSecondMessage());
                 transactionService.save(transaction);

            });
            statementRepository.save(statement);

        });

        return statements;
    }



}
