package com.media.solutions.task.domain.enumeration;

public enum ContentType {

    HEADER("074"),
    TRANSACTION_DETAIL("075"),
    SUPPLEMENTARY_INFORMATION("076"),
    MESSAGE_PART1("078"),
    MESSAGE_PART2("079");


    private String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
