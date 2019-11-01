package com.media.solutions.task.gpc.readers.parsers.impl;

import com.media.solutions.task.domain.TransactionDetails;
import com.media.solutions.task.gpc.readers.parsers.TransactionParser;
import com.media.solutions.task.gpc.readers.positions.TransactionDetailsPosition;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TransactionDetailsParser implements TransactionParser<TransactionDetails> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

    //Parsing Transaction Details and using this method in the StatementParserImpl class

    @Override
    public TransactionDetails parse(String line) {

        TransactionDetails transactionDetails = null;

        if (!StringUtils.isEmpty(line)) {

            transactionDetails = new TransactionDetails();
            try {

                transactionDetails.setAccountNumber(line.substring(TransactionDetailsPosition.ACCOUNT_NUMBER_START,
                        TransactionDetailsPosition.ACCOUNT_NUMBER_END));
                transactionDetails.setCounterPartAccountNumber(line.substring(TransactionDetailsPosition.COUNTERPARTY_ACCOUNT_NUMBER_START,
                        TransactionDetailsPosition.COUNTERPARTY_ACCOUNT_NUMBER_END));
                transactionDetails.setTransactionIdentifier(line.substring(TransactionDetailsPosition.TRANSACTION_IDENTIFIER_START,
                        TransactionDetailsPosition.TRANSACTION_IDENTIFIER_END));
                transactionDetails.setAccountType(line.charAt(TransactionDetailsPosition.ACCOUNTING_TYPE));
                transactionDetails.setVariableCode(line.substring(TransactionDetailsPosition.VARIABLE_CODE_START,
                        TransactionDetailsPosition.VARIABLE_CODE_END));
                transactionDetails.setBankCode(line.substring(TransactionDetailsPosition.COUNTERPARTY_BANK_CODE_START,
                        TransactionDetailsPosition.COUNTERPARTY_BANK_CODE_END));
                transactionDetails.setConstantCode(line.substring(TransactionDetailsPosition.CONSTANT_CODE_START,
                        TransactionDetailsPosition.CONSTANT_CODE_END));
                transactionDetails.setSpecificCode(line.substring(TransactionDetailsPosition.SPECIFIC_CODE_START,
                        TransactionDetailsPosition.SPECIFIC_CODE_END));
                transactionDetails.setCounterPartyNameOrDescription(line.substring(TransactionDetailsPosition.COUNTERPARTY_NAME_OR_DESCRIPTION_START,
                        TransactionDetailsPosition.COUNTERPARTY_NAME_OR_DESCRIPTION_END));
                transactionDetails.setCurrencyCode(line.substring(TransactionDetailsPosition.CURRENCY_CODE_START,
                        TransactionDetailsPosition.CURRENCY_CODE_END));
                transactionDetails.setTransactionAmount(line.substring(TransactionDetailsPosition.TRANSACTION_AMOUNT_START,
                        TransactionDetailsPosition.TRANSACTION_AMOUNT_END));
                transactionDetails.setPostingDate(LocalDate.parse(line.substring(TransactionDetailsPosition.POSTING_DATE_START,
                        TransactionDetailsPosition.POSTING_DATE_END), formatter));
                transactionDetails.setValueDate(LocalDate.parse(line.substring(TransactionDetailsPosition.VALUE_DATE_START,
                        TransactionDetailsPosition.VALUE_DATE_END), formatter));
                transactionDetails.setDelimiter(line.substring(TransactionDetailsPosition.DELIMITER_START,
                        TransactionDetailsPosition.DELIMITER_END));

            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("File could not be parsed. Invalid Date", e.getParsedString(), e.getErrorIndex());
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }
        }
        return transactionDetails;
    }
}
