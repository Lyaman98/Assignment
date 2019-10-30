package com.media.solutions.task.domain;

import com.media.solutions.task.domain.enumeration.ContentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Header {

    @Id
    private String id;
    private final String contentType = ContentType.HEADER.getValue();
    private final int debitBalanceSign = 0;
    private final int CreditBalanceSign = 0;
    private String accountNumber;
    private LocalDate openingBalanceDate;
    private String accountOwner;
    private String openingBalance;
    private char openingSign;
    private String closingBalance;
    private char closingSign;
    private String sumOfDebit;
    private String sumOfCredit;
    private String statementSerialNumber;
    private LocalDate statementDate;


    public String getContentType() {
        return contentType;
    }

    public LocalDate getOpeningBalanceDate() {
        return openingBalanceDate;
    }

    public void setOpeningBalanceDate(LocalDate openingBalanceDate) {
        this.openingBalanceDate = openingBalanceDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public char getOpeningSign() {
        return openingSign;
    }

    public void setOpeningSign(char openingSign) {
        this.openingSign = openingSign;
    }

    public String getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }

    public char getClosingSign() {
        return closingSign;
    }

    public void setClosingSign(char closingSign) {
        this.closingSign = closingSign;
    }

    public String getSumOfDebit() {
        return sumOfDebit;
    }

    public void setSumOfDebit(String sumOfDebit) {
        this.sumOfDebit = sumOfDebit;
    }

    public String getSumOfCredit() {
        return sumOfCredit;
    }

    public void setSumOfCredit(String sumOfCredit) {
        this.sumOfCredit = sumOfCredit;
    }

    public String getStatementSerialNumber() {
        return statementSerialNumber;
    }

    public void setStatementSerialNumber(String statementSerialNumber) {
        this.statementSerialNumber = statementSerialNumber;
    }

    public LocalDate getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(LocalDate statementDate) {
        this.statementDate = statementDate;
    }

    @Override
    public String toString() {
        return "Header{" +
                "id='" + id + '\'' +
                ", contentType=" + contentType +
                ", debitBalanceSign=" + debitBalanceSign +
                ", CreditBalanceSign=" + CreditBalanceSign +
                ", accountNumber=" + accountNumber +
                ", openingBalanceDate=" + openingBalanceDate +
                ", accountOwner='" + accountOwner + '\'' +
                ", openingBalance=" + openingBalance +
                ", openingSign=" + openingSign +
                ", closingBalance=" + closingBalance +
                ", closingSign=" + closingSign +
                ", sumOfDebit=" + sumOfDebit +
                ", sumOfCredit=" + sumOfCredit +
                ", statementSerialNumber=" + statementSerialNumber +
                ", statementDate=" + statementDate +
                '}';
    }
}
