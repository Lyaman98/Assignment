package com.media.solutions.task.gpc.readers.parsers.impl;

import com.media.solutions.task.domain.TransactionSecondMessage;
import com.media.solutions.task.gpc.readers.parsers.TransactionParser;
import com.media.solutions.task.gpc.readers.positions.TransactionSecondMessagePosition;
import org.springframework.util.StringUtils;

public class TransactionSecondMessageParser implements TransactionParser<TransactionSecondMessage> {

    //Parsing Second Message of a Transaction and using this method in the StatementParserImpl class

    @Override
    public TransactionSecondMessage parse(String line) {
        TransactionSecondMessage transactionSecondMessage = null;

        if (!StringUtils.isEmpty(line)) {

            transactionSecondMessage = new TransactionSecondMessage();

            try {
                transactionSecondMessage.setThirdPart(line.substring(TransactionSecondMessagePosition.THIRD_PART_START,
                        TransactionSecondMessagePosition.THIRD_PART_END));
                transactionSecondMessage.setFourthPart(line.substring(TransactionSecondMessagePosition.FOURTH_PART_START,
                        TransactionSecondMessagePosition.FOURTH_PART_END));
                transactionSecondMessage.setOther(line.substring(TransactionSecondMessagePosition.OTHER_START,
                        TransactionSecondMessagePosition.OTHER_END));

            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }

        }

        return transactionSecondMessage;

    }
}
