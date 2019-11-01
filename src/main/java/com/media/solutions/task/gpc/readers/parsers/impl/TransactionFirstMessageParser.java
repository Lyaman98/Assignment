package com.media.solutions.task.gpc.readers.parsers.impl;

import com.media.solutions.task.domain.TransactionFirstMessage;
import com.media.solutions.task.gpc.readers.parsers.TransactionParser;
import com.media.solutions.task.gpc.readers.positions.TransactionFirstMessagePosition;
import org.springframework.util.StringUtils;

public class TransactionFirstMessageParser implements TransactionParser<TransactionFirstMessage> {

    //Parsing First Message of a Transaction and using this method in the StatementParserImpl class

    @Override
    public TransactionFirstMessage parse(String line) {

        TransactionFirstMessage firstMessage = null;

        if (!StringUtils.isEmpty(line)) {

            firstMessage = new TransactionFirstMessage();

            try {

                firstMessage.setFirstPart(line.substring(TransactionFirstMessagePosition.FIRST_PART_START,
                        TransactionFirstMessagePosition.FIRST_PART_END));
                firstMessage.setSecondPart(line.substring(TransactionFirstMessagePosition.SECOND_PART_START,
                        TransactionFirstMessagePosition.SECOND_PART_END));

            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }
        }
        return firstMessage;

    }
}
