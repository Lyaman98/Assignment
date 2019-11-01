package com.media.solutions.task.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;

@Document
public class Statement {

    @Id
    private String id;
    private StatementHeader statementHeader;
    private LinkedList<Transaction> transactions = new LinkedList<>();


    public LinkedList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(LinkedList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public StatementHeader getStatementHeader() {
        return statementHeader;
    }

    public void setStatementHeader(StatementHeader statementHeader) {
        this.statementHeader = statementHeader;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
