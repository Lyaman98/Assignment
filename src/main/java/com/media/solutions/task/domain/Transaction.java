package com.media.solutions.task.domain;

import org.springframework.data.annotation.Id;

public class Transaction {

    @Id
    private String id;
    private TransactionDetails transactionDetails;
    private TransactionSupplementaryDetails transactionSupplementaryDetails;
    private TransactionFirstMessage firstMessage;
    private TransactionSecondMessage transactionSecondMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public TransactionSupplementaryDetails getTransactionSupplementaryDetails() {
        return transactionSupplementaryDetails;
    }

    public void setTransactionSupplementaryDetails(TransactionSupplementaryDetails transactionSupplementaryDetails) {
        this.transactionSupplementaryDetails = transactionSupplementaryDetails;
    }

    public TransactionFirstMessage getFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(TransactionFirstMessage firstMessage) {
        this.firstMessage = firstMessage;
    }

    public TransactionSecondMessage getTransactionSecondMessage() {
        return transactionSecondMessage;
    }

    public void setTransactionSecondMessage(TransactionSecondMessage transactionSecondMessage) {
        this.transactionSecondMessage = transactionSecondMessage;
    }
}
