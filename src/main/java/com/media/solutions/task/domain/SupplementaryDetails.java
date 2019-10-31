package com.media.solutions.task.domain;

import com.media.solutions.task.domain.enumeration.ContentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class SupplementaryDetails {

    @Id
    private String id;
    private String transactionIdentification;
    private LocalDate debitingDate;
    private final String contentType = ContentType.SUPPLEMENTARY_INFORMATION.getValue();
    private String counterPartyNameOrComment;

    public String getCounterPartyNameOrComment() {
        return counterPartyNameOrComment;
    }

    public void setCounterPartyNameOrComment(String counterPartyNameOrComment) {
        this.counterPartyNameOrComment = counterPartyNameOrComment;
    }

    public String getContentType() {
        return contentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionIdentification() {
        return transactionIdentification;
    }

    public void setTransactionIdentification(String transactionIdentification) {
        this.transactionIdentification = transactionIdentification;
    }

    public LocalDate getDebitingDate() {
        return debitingDate;
    }

    public void setDebitingDate(LocalDate debitingDate) {
        this.debitingDate = debitingDate;
    }
}
