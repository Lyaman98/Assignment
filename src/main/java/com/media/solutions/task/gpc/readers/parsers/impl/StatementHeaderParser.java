package com.media.solutions.task.gpc.readers.parsers.impl;

import com.media.solutions.task.domain.StatementHeader;
import com.media.solutions.task.gpc.readers.parsers.TransactionParser;
import com.media.solutions.task.gpc.readers.positions.StatementHeaderPositions;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class StatementHeaderParser implements TransactionParser<StatementHeader> {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

    //Parsing Header of a Statement and using this method in the StatementParserImpl class

    @Override
    public StatementHeader parse(String line) {

        StatementHeader statementHeader = null;

        if (!StringUtils.isEmpty(line)) {

            statementHeader = new StatementHeader();

            try {
                statementHeader.setAccountNumber(line.substring(StatementHeaderPositions.ACCOUNT_NUMBER_START,
                        StatementHeaderPositions.ACCOUNT_NUMBER_END));
                statementHeader.setAccountOwner(line.substring(StatementHeaderPositions.ACCOUNT_OWNER_START,
                        StatementHeaderPositions.ACCOUNT_OWNER_END));
                statementHeader.setOpeningBalance(line.substring(StatementHeaderPositions.OPENING_BALANCE_START
                        , StatementHeaderPositions.OPENING_BALANCE_END));
                statementHeader.setOpeningSign(line.charAt(StatementHeaderPositions.OPENING_BALANCE_SIGN));
                statementHeader.setClosingBalance(line.substring(StatementHeaderPositions.CLOSING_BALANCE_START
                        , StatementHeaderPositions.CLOSING_BALANCE_END));
                statementHeader.setClosingSign(line.charAt(StatementHeaderPositions.CLOSING_BALANCE_SIGN));
                statementHeader.setSumOfDebit(line.substring(StatementHeaderPositions.SUM_OF_DEBIT_START
                        , StatementHeaderPositions.SUM_OF_DEBIT_END));
                statementHeader.setSumOfCredit(line.substring(StatementHeaderPositions.SUM_OF_CREDIT_START
                        , StatementHeaderPositions.SUM_OF_CREDIT_END));
                statementHeader.setStatementSerialNumber(line.substring(StatementHeaderPositions.SERIAL_NUMBER_START,
                        StatementHeaderPositions.SERIAL_NUMBER_END));
                statementHeader.setDebitSign(line.charAt(StatementHeaderPositions.SIGN_FOR_DEBIT));
                statementHeader.setCreditBalanceSign(line.charAt(StatementHeaderPositions.SIGN_FOR_CREDIT));

                statementHeader.setOpeningBalanceDate(LocalDate.parse(line.substring(StatementHeaderPositions.OPENING_BALANCE_DATE_START
                        , StatementHeaderPositions.OPENING_BALANCE_DATE_END), formatter));
                statementHeader.setStatementDate(LocalDate.parse(line.substring(StatementHeaderPositions.STATEMENT_DATE_START
                        , StatementHeaderPositions.STATEMENT_DATE_END), formatter));

            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("File could not be parsed. Invalid Date", e.getParsedString(), e.getErrorIndex());
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }
        }

        return statementHeader;
    }

}
