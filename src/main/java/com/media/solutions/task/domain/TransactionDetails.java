package com.media.solutions.task.domain;

import com.media.solutions.task.domain.enumeration.ContentType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.HashMap;

@Document
public class TransactionDetails {

    @Id
    private String id;
    private final String contentType = ContentType.TRANSACTION_DETAIL.getValue();
    private final String delimiter = "00";
    private String accountNumber;
    private String counterPartAccountNumber;
    private String transactionIdentifier;
    private String transactionAmount;
    private String accountType;
    private String variableCode;
    private String bankCode;
    private String constantCode;
    private String specificCode;
    private LocalDate valueDate;
    private String counterPartyNameOrDescription;
    private String transactionDescription;
    private String currencyCode;
    private LocalDate postingDate;

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public void setCounterPartyNameOrDescription(String counterPartyNameOrDescription) {
        this.counterPartyNameOrDescription = counterPartyNameOrDescription;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCounterPartyNameOrDescription() {
        return counterPartyNameOrDescription;
    }

    public String getDelimeter() {
        return delimiter;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCounterPartAccountNumber() {
        return counterPartAccountNumber;
    }

    public void setCounterPartAccountNumber(String counterPartAccountNumber) {
        this.counterPartAccountNumber = counterPartAccountNumber;
    }

    public String getTransactionIdentifier() {
        return transactionIdentifier;
    }

    public void setTransactionIdentifier(String transactionIdentifier) {
        this.transactionIdentifier = transactionIdentifier;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }



    public String getVariableCode() {
        return variableCode;
    }

    public void setVariableCode(String variableCode) {
        this.variableCode = variableCode;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getConstantCode() {
        return constantCode;
    }

    public void setConstantCode(String constantCode) {
        this.constantCode = constantCode;
    }

    public String getSpecificCode() {
        return specificCode;
    }

    public void setSpecificCode(String specificCode) {
        this.specificCode = specificCode;
    }

    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }

    public String getConterpartyName() {
        return counterPartyNameOrDescription;
    }

    public void setConterpartyName(String conterpartyName) {
        this.counterPartyNameOrDescription = conterpartyName;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public LocalDate getPostingDate() {
        return postingDate;
    }

    public void setPostingDate(LocalDate postingDate) {
        this.postingDate = postingDate;
    }


    @Override
    public String toString() {
        return "TransactionDetailsRepository{" +
                "id='" + id + '\'' +
                ", contentTyp='" + contentType + '\'' +
                ", accountNumber=" + accountNumber +
                ", counterPartAccountNumber=" + counterPartAccountNumber +
                ", transactionIdentifier=" + transactionIdentifier +
                ", transactionAmount=" + transactionAmount +
                ", accountType=" + accountType +
                ", variableCode=" + variableCode +
                ", bankCode=" + bankCode +
                ", constantCode=" + constantCode +
                ", specificCode=" + specificCode +
                ", valueDate=" + valueDate +
                ", conterpartyName='" + counterPartyNameOrDescription + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", postingDate=" + postingDate +
                '}';
    }
}
