package com.media.solutions.task.domain;

import com.media.solutions.task.domain.enumeration.ContentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TransactionSecondMessage {

    @Id
    private String id;
    private String contentType = ContentType.MESSAGE_PART1.getValue();
    private String thirdPart;
    private String fourthPart;
    private String other;

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

    public String getThirdPart() {
        return thirdPart;
    }

    public void setThirdPart(String thirdPart) {
        this.thirdPart = thirdPart;
    }

    public String getFourthPart() {
        return fourthPart;
    }

    public void setFourthPart(String fourthPart) {
        this.fourthPart = fourthPart;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
