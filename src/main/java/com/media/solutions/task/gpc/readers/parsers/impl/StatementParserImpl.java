package com.media.solutions.task.gpc.readers.parsers.impl;

import com.media.solutions.task.domain.*;
import com.media.solutions.task.domain.enumeration.ContentType;
import com.media.solutions.task.gpc.readers.parsers.StatementParser;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class StatementParserImpl implements StatementParser {

    private StatementHeaderParser headerParser = new StatementHeaderParser();
    private TransactionDetailsParser transactionDetailsParser = new TransactionDetailsParser();
    private TransactionSupplementaryDetailsParser supplementaryDetailsParser = new TransactionSupplementaryDetailsParser();
    private TransactionFirstMessageParser firstMessageParser = new TransactionFirstMessageParser();
    private TransactionSecondMessageParser secondMessageParser = new TransactionSecondMessageParser();
    private LinkedList<Statement> statements = new LinkedList<>();


    @Override
    public List<Statement> parse(InputStreamReader inputStreamReader) {

        try(BufferedReader reader = new BufferedReader(inputStreamReader)) {

            String line;

            while ((line = reader.readLine()) != null){

                if (StringUtils.isEmpty(line)) continue;

                //first, checking the beginning of the file and then creating the appropriate object
                if (headerParser.isParsable(line, ContentType.HEADER.getValue())){
                    createNewStatement(headerParser.parse(line));
                }else if(transactionDetailsParser.isParsable(line, ContentType.TRANSACTION_DETAIL.getValue())){
                    createNewTransactionForStatement(transactionDetailsParser.parse(line));
                }else if(supplementaryDetailsParser.isParsable(line, ContentType.SUPPLEMENTARY_INFORMATION.getValue())){
                    setSupplementaryDetailsForTransaction(supplementaryDetailsParser.parse(line));
                }else if(firstMessageParser.isParsable(line,ContentType.MESSAGE_PART1.getValue())){
                    setFirstMessageForTransaction(firstMessageParser.parse(line));
                }else if(secondMessageParser.isParsable(line,ContentType.MESSAGE_PART2.getValue())){
                    setSecondMessageForTransaction(secondMessageParser.parse(line));
                }else {
                    throw new IllegalArgumentException("Incorrect Data");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return statements;
    }



    //creating new Statement if the line starts with Header
    //and adding Statement to the list of Statements
    private void createNewStatement(StatementHeader statementHeader) {
        Statement statement = new Statement();
        statement.setStatementHeader(statementHeader);
        statements.add(statement);
    }

    //Creating list of transactions for each Statement
    private void createNewTransactionForStatement(TransactionDetails transactionDetails) {
        Statement statement = statements.getLast();
        Transaction transaction = new Transaction();
        transaction.setTransactionDetails(transactionDetails);
        statement.getTransactions().add(transaction);
    }
    private void setSupplementaryDetailsForTransaction(TransactionSupplementaryDetails transactionSupplementaryDetails) {
        Statement statement = statements.getLast();
        Transaction transaction = statement.getTransactions().getLast();
        transaction.setTransactionSupplementaryDetails(transactionSupplementaryDetails);

    }

    private void setFirstMessageForTransaction(TransactionFirstMessage transactionFirstMessage) {
        Statement statement = statements.getLast();
        Transaction transaction = statement.getTransactions().getLast();
        transaction.setFirstMessage(transactionFirstMessage);
    }
    private void setSecondMessageForTransaction(TransactionSecondMessage transactionSecondMessage) {
        Statement statement = statements.getLast();
        Transaction transaction = statement.getTransactions().getLast();
        transaction.setTransactionSecondMessage(transactionSecondMessage);
    }

}
