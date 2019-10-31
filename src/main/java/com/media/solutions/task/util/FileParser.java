package com.media.solutions.task.util;

import com.media.solutions.task.domain.*;
import com.media.solutions.task.domain.enumeration.ContentType;
import com.media.solutions.task.exceptions.HeaderNotFoundException;
import com.media.solutions.task.exceptions.TransactionDetailsNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileParser {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

    //passing String to this method and creating Header object
    private Optional<Header> getHeader(String headerInfo) {

        Header header = null;

        if (headerInfo != null) {

            header = new Header();

            header.setAccountOwner(headerInfo.substring(19, 39));
            header.setOpeningSign(headerInfo.charAt(59));
            header.setClosingSign(headerInfo.charAt(74));
            header.setAccountNumber(headerInfo.substring(3, 19));
            header.setStatementSerialNumber(headerInfo.substring(105, 108));
            header.setOpeningBalance(headerInfo.substring(45, 51));
            header.setOpeningBalance(headerInfo.substring(45, 59));
            header.setClosingBalance(headerInfo.substring(60, 74));
            header.setSumOfDebit(headerInfo.substring(75, 89));
            header.setSumOfCredit(headerInfo.substring(89, 104));

            try {

                header.setOpeningBalanceDate(LocalDate.parse(headerInfo.substring(39, 45), formatter));
                header.setStatementDate(LocalDate.parse(headerInfo.substring(108, 114), formatter));

            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (DateTimeParseException e) {
                System.out.println(e);
            }
        }
        return Optional.ofNullable(header);
    }

    //passing String to this method and creating TransactionDetails object
    private Optional<TransactionDetails> getTransactionDetails(String transactionInfo) throws DateTimeParseException, StringIndexOutOfBoundsException {
        TransactionDetails transactionDetails = null;

        if (transactionInfo != null) {

            transactionDetails = new TransactionDetails();
            try {

                transactionDetails.setAccountNumber(transactionInfo.substring(3, 19));
                transactionDetails.setCounterPartAccountNumber(transactionInfo.substring(19, 35));
                transactionDetails.setTransactionIdentifier(transactionInfo.substring(35, 48));
                transactionDetails.setAccountType(transactionInfo.substring(60, 60));
                transactionDetails.setVariableCode(transactionInfo.substring(61, 71));
                transactionDetails.setBankCode(transactionInfo.substring(71, 73));
                transactionDetails.setConstantCode(transactionInfo.substring(77, 81));
                transactionDetails.setSpecificCode(transactionInfo.substring(81, 91));
                transactionDetails.setCounterPartyNameOrDescription(transactionInfo.substring(97, 117));
                transactionDetails.setCurrencyCode(transactionInfo.substring(117, 122));
                transactionDetails.setTransactionAmount(transactionInfo.substring(48, 60));


                transactionDetails.setPostingDate(LocalDate.parse(transactionInfo.substring(122, 128), formatter));
                transactionDetails.setValueDate(LocalDate.parse(transactionInfo.substring(91, 97), formatter));

            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("File could not be parsed. Invalid Date", e.getParsedString(), e.getErrorIndex());
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }
        }
        return Optional.ofNullable(transactionDetails);

    }

    //passing String to this method and creating SupplementaryDetails object
    private Optional<SupplementaryDetails> getSupplementaryDetails(String supplementaryInfo) throws DateTimeParseException, StringIndexOutOfBoundsException {

        SupplementaryDetails supplementaryDetails = null;

        if (supplementaryInfo != null) {
            supplementaryDetails = new SupplementaryDetails();
            try {
                supplementaryDetails.setTransactionIdentification(supplementaryInfo.substring(3, 29));
                supplementaryDetails.setCounterPartyNameOrComment(supplementaryInfo.substring(35, 117));


                supplementaryDetails.setDebitingDate(LocalDate.parse(supplementaryInfo.substring(29, 35)));
            } catch (DateTimeParseException e) {
                throw new DateTimeParseException("File could not be parsed.Invalid Date", e.getParsedString(), e.getErrorIndex());
            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }
        }

        return Optional.ofNullable(supplementaryDetails);
    }

    //passing String to this method and creating Message to the beneficiary (1/2)
    private Optional<FirstMessage> getFirstMessage(String firstMessageInfo) throws StringIndexOutOfBoundsException {
        FirstMessage firstMessage = null;

        if (firstMessageInfo != null) {

            firstMessage = new FirstMessage();

            try {

                firstMessage.setFirstPart(firstMessageInfo.substring(3, 38));
                firstMessage.setSecondPart(firstMessageInfo.substring(38, 73));

            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }
        }

        return Optional.ofNullable(firstMessage);
    }

    //passing String to this method and creating Message to the beneficiary (2/2)
    private Optional<SecondMessage> getSecondMessage(String secondMessageInfo) throws StringIndexOutOfBoundsException {
        SecondMessage secondMessage = null;

        if (secondMessageInfo != null) {

            secondMessage = new SecondMessage();

            try {
                secondMessage.setThirdPart(secondMessageInfo.substring(3, 38));
                secondMessage.setFourthPart(secondMessageInfo.substring(38, 73));
                secondMessage.setOther(secondMessageInfo.substring(73, 109));

            } catch (StringIndexOutOfBoundsException e) {
                throw new StringIndexOutOfBoundsException("Invalid file");
            }

        }

        return Optional.ofNullable(secondMessage);
    }

    //passing File object to this method and getting the list of statements that could be inside of the file
    public List<Statement> getFile(MultipartFile file) throws FileNotFoundException {
        String line;
        List<Statement> statementList = new ArrayList<>();
        Transaction transaction = new Transaction();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

            Statement statement;
            List<Transaction> transactionList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;


                //getting first three symbols to get the contentType
                String contentType = line.substring(0, 3);

                if (contentType.equals(ContentType.HEADER.getValue())) {

                    //if line begins with "074" then the new Statement and the list of Transactions for the statement
                    //are created

                    statement = new Statement();
                    transactionList = new ArrayList<>();
                    statementList.add(statement);

                    statement.setTransactions(transactionList);
                    getHeader(line).ifPresent(statement::setHeader);
                }
                if (contentType.equals(ContentType.TRANSACTION_DETAIL.getValue())) {

                    //if line begins with "076" then new Transaction object
                    // is created and is added to the list of transactions

                    transaction = new Transaction();
                    transactionList.add(transaction);

                    getTransactionDetails(line).ifPresent(transaction::setTransactionDetails);

                }

                if (contentType.equals(ContentType.SUPPLEMENTARY_INFORMATION.getValue())) {
                    getSupplementaryDetails(line).ifPresent(transaction::setSupplementaryDetails);
                }

                if (contentType.equals(ContentType.MESSAGE_PART1.getValue())) {
                    getFirstMessage(line).ifPresent(transaction::setFirstMessage);
                }

                if (contentType.equals(ContentType.MESSAGE_PART2.getValue())) {
                    getSecondMessage(line).ifPresent(transaction::setSecondMessage);
                }
            }
            if (statementList.isEmpty()) {
                throw new HeaderNotFoundException("Statement header not found");
            }

            if (transactionList.isEmpty()) {
                throw new TransactionDetailsNotFoundException("Transaction Details not found");
            }

        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File was not found");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return statementList;
    }

}
