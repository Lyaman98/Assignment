package com.media.solutions.task.gpc.readers.parsers.impl;

import com.media.solutions.task.domain.StatementHeader;
import com.media.solutions.task.domain.enumeration.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class StatementHeaderParserTest {

    private DateTimeFormatter formatter;
    StatementHeaderParser statementHeaderParser;

    @BeforeEach
    void initialize(){
        formatter = DateTimeFormatter.ofPattern("ddMMyy");
        statementHeaderParser = new StatementHeaderParser();
    }

    @Test
    void parse() {
        StatementHeader statementHeader = statementHeaderParser
                .parse("0740000002600848022Mgr. Yyyyyy, Xxxxx  25071900000003549468+00000001782020+000000115536480000000097862000000250819FIO");

        assertEquals(ContentType.HEADER.getValue(), statementHeader.getContentType());
        assertEquals("0000002600848022", statementHeader.getAccountNumber());
        assertEquals("Mgr. Yyyyyy, Xxxxx  ",statementHeader.getAccountOwner());
        assertEquals(LocalDate.parse("250719",formatter),statementHeader.getOpeningBalanceDate());
        assertEquals("00000003549468", statementHeader.getOpeningBalance());
        assertEquals("00000001782020",statementHeader.getClosingBalance());
        assertEquals("00000011553648",statementHeader.getSumOfDebit());
        assertEquals("00000009786200",statementHeader.getSumOfCredit());
        assertEquals(LocalDate.parse("250819",formatter),statementHeader.getStatementDate());



    }
}
