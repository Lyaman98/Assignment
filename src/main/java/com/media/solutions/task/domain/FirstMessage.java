package com.media.solutions.task.domain;

import com.media.solutions.task.domain.enumeration.ContentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FirstMessage {

    @Id
    private String id;
    private String contentType = ContentType.MESSAGE_PART1.getValue();
    private String firstPart;
    private String secondPart;
    private String originalCurrencyAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getFirstPart() {
        return firstPart;
    }

    public void setFirstPart(String firstPart) {
        this.firstPart = firstPart;
    }

    public String getSecondPart() {
        return secondPart;
    }

    public void setSecondPart(String secondPart) {
        this.secondPart = secondPart;
    }

    public String getOriginalCurrencyAmount() {
        return originalCurrencyAmount;
    }

    public void setOriginalCurrencyAmount(String originalCurrencyAmount) {
        this.originalCurrencyAmount = originalCurrencyAmount;
    }
}
