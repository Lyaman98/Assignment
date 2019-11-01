package com.media.solutions.task.gpc.readers.parsers.impl;

import com.media.solutions.task.domain.TransactionDetails;
import com.media.solutions.task.domain.enumeration.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDetailsParserTest {

    private DateTimeFormatter formatter;
    TransactionDetailsParser transactionDetailsParser;

    @BeforeEach
    void initialize(){
        formatter = DateTimeFormatter.ofPattern("ddMMyy");
        transactionDetailsParser = new TransactionDetailsParser();
    }

    @Test
    void parse() {

        TransactionDetails transactionDetails = transactionDetailsParser
                .parse("0750000002600848022000000000000000000213829734180000001596001000000310100000000000000000000210719N�kup: Misto1234-Obu00203250719");

        assertEquals(ContentType.TRANSACTION_DETAIL.getValue(),transactionDetails.getContentType());
        assertEquals("0000002600848022",transactionDetails.getAccountNumber());
        assertEquals("0000000000000000",transactionDetails.getCounterPartAccountNumber());
        assertEquals("0021382973418",transactionDetails.getTransactionIdentifier());
        assertEquals("000000159600",transactionDetails.getTransactionAmount());
        assertEquals('1',transactionDetails.getAccountType());
        assertEquals("0000003101",transactionDetails.getVariableCode());
        assertEquals("00",transactionDetails.getDelimiter());
        assertEquals("0000",transactionDetails.getBankCode());
        assertEquals("0000",transactionDetails.getConstantCode());
        assertEquals("0000000000",transactionDetails.getSpecificCode());
        assertEquals(LocalDate.parse("210719",formatter),transactionDetails.getValueDate());
        assertEquals("N�kup: Misto1234-Obu",transactionDetails.getConterpartyName());
        assertEquals("00203",transactionDetails.getCurrencyCode());
        assertEquals(LocalDate.parse("250719",formatter),transactionDetails.getPostingDate());

    }
}
