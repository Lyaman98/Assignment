package com.media.solutions.task.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Transaction {

    @Id
    private String id;

    @DBRef
    private TransactionDetails transactionDetails;

    @DBRef
    private SupplementaryDetails supplementaryDetails;

    @DBRef
    private FirstMessage firstMessage;

    @DBRef
    private SecondMessage secondMessage;

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

    public SupplementaryDetails getSupplementaryDetails() {
        return supplementaryDetails;
    }

    public void setSupplementaryDetails(SupplementaryDetails supplementaryDetails) {
        this.supplementaryDetails = supplementaryDetails;
    }

    public FirstMessage getFirstMessage() {
        return firstMessage;
    }

    public void setFirstMessage(FirstMessage firstMessage) {
        this.firstMessage = firstMessage;
    }

    public SecondMessage getSecondMessage() {
        return secondMessage;
    }

    public void setSecondMessage(SecondMessage secondMessage) {
        this.secondMessage = secondMessage;
    }
}
