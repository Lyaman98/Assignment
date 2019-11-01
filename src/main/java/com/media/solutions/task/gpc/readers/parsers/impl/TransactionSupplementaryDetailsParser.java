package com.media.solutions.task.gpc.readers.parsers.impl;

import com.media.solutions.task.domain.TransactionSupplementaryDetails;
import com.media.solutions.task.gpc.readers.parsers.TransactionParser;
import com.media.solutions.task.gpc.readers.positions.TransactionSupplementaryDetailsPosition;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TransactionSupplementaryDetailsParser implements TransactionParser<TransactionSupplementaryDetails> {

    //Parsing Supplementary Details of a Transaction and using this method in the StatementParserImpl class

    @Override
    public TransactionSupplementaryDetails parse(String line) {

        TransactionSupplementaryDetails transactionSupplementaryDetails = null;

        if (!StringUtils.isEmpty(line)) {

            transactionSupplementaryDetails = new TransactionSupplementaryDetails();
            try {
                transactionSupplementaryDetails.setTransactionIdentification(line.substring(
                        TransactionSupplementaryDetailsPosition.TRANSACTION_IDENTIFICATION_START,
                        TransactionSupplementaryDetailsPosition.TRANSACTION_IDENTIFICATION_END));
                transactionSupplementaryDetails.setCounterPartyNameOrComment(line.substring(
                        TransactionSupplementaryDetailsPosition.COUNTERPARTY_NAME_OR_COMMENT_START,
                        TransactionSupplementaryDetailsPosition.COUNTERPARTY_NAME_OR_COMMENT_END));

                transactionSupplementaryDetails.setDebitingDate(LocalDate.parse(line.substring(
                        TransactionSupplementaryDetailsPosition.DATE_OF_DEBITING_START,
                        TransactionSupplementaryDetailsPosition.DATE_OF_DEBITING_END)));
            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("File could not be parsed.Invalid Date", e.getParsedString(), e.getErrorIndex());
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }
        }
        return transactionSupplementaryDetails;
    }
}
