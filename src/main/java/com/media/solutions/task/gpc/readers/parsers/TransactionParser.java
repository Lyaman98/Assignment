package com.media.solutions.task.gpc.readers.parsers;

import org.springframework.util.StringUtils;

public interface TransactionParser<T> {

    T parse(String line);

    default boolean isParsable(String line, String contentType){
        return StringUtils.startsWithIgnoreCase(line, contentType);
    }

}
