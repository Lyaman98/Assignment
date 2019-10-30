package com.media.solutions.task.util;

import com.media.solutions.task.domain.*;
import com.media.solutions.task.domain.enumeration.ContentType;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileParser {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");

    //getting the first line in the file and creating Header object to store in the database
    public Optional<Header> getHeader(String headerInfo) {

//        String headerInfo = getFileLine(file, ContentType.HEADER.getValue());
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

    public Optional<TransactionDetails> getTransactionDetails(String transactionInfo) {
//        String transactionInfo = getFileLine(file, ContentType.TRANSACTION_DETAIL.getValue());
        TransactionDetails transactionDetails = null;

        if (transactionInfo != null) {

            transactionDetails = new TransactionDetails();

            transactionDetails.setAccountNumber(transactionInfo.substring(3, 19));
            transactionDetails.setCounterPartAccountNumber(transactionInfo.substring(19, 35));
            transactionDetails.setTransactionIdentifier(transactionInfo.substring(35, 48));
            transactionDetails.setAccountType(transactionInfo.substring(60, 60));
            transactionDetails.setVariableCode(transactionInfo.substring(61, 71));
            transactionDetails.setBankCode(transactionInfo.substring(71, 73));
            transactionDetails.setConstantCode(transactionInfo.substring(77, 81));
            transactionDetails.setSpecificCode(transactionInfo.substring(81, 91));
            transactionDetails.setValueDate(LocalDate.parse(transactionInfo.substring(91, 97), formatter));
            transactionDetails.setCounterPartyNameOrDescription(transactionInfo.substring(97, 117));
            transactionDetails.setCurrencyCode(transactionInfo.substring(117, 122));
            transactionDetails.setPostingDate(LocalDate.parse(transactionInfo.substring(122, 128), formatter));
            transactionDetails.setTransactionAmount(transactionInfo.substring(48, 60));
        }
        return Optional.ofNullable(transactionDetails);

    }

    public Optional<SuplementaryDetails> getSuplementaryDetails(String suplementaryInfo) {

//        String suplementaryInfo = getFileLine(file, ContentType.SUPLEMENTARY_INFORMATION.getValue());
        SuplementaryDetails suplementaryDetails = null;

        if (suplementaryInfo != null) {
            suplementaryDetails = new SuplementaryDetails();
            suplementaryDetails.setTransactionIdentification(suplementaryInfo.substring(3, 29));
            suplementaryDetails.setCounterPartyNameOrComment(suplementaryInfo.substring(35,117));

            //TODO : catch unparsable date exception
//            suplementaryDetails.setDebitingDate(LocalDate.parse(suplementaryInfo.substring(29, 35)));
        }

        return Optional.ofNullable(suplementaryDetails);
    }


    public List<Statement> getFile(File file) {
        String line = null;
        List<Statement> statementList = new ArrayList<>();
        Transaction transaction = new Transaction();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(file.getPath()));
            Statement statement;
            List<Transaction> transactionList = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) continue;

                String contentType = line.substring(0, 3);

                if (contentType.equals(ContentType.HEADER.getValue())){
                    statement = new Statement();
                    transactionList = new ArrayList<>();
                    statementList.add(statement);

                    statement.setTransactions(transactionList);
                    getHeader(line).ifPresent(statement::setHeader);
                }
                if (contentType.equals(ContentType.TRANSACTION_DETAIL.getValue())) {
                    transaction = new Transaction();
                    transactionList.add(transaction);

                    getTransactionDetails(line).ifPresent(transaction::setTransactionDetails);

                }

                if (contentType.equals(ContentType.SUPLEMENTARY_INFORMATION.getValue())){
                    getSuplementaryDetails(line).ifPresent(transaction::setSuplementaryDetails);
                }
//
//                if (contentType.equals(ContentType.MESSAGE_PART1.getValue())){
//                    getFirstMessage(line).ifPresent(transaction::setFirstMessage);
//                }
//
//                if (contentType.equals(ContentType.MESSAGE_PART2.getValue())){
//                    getSecondMessage(line).ifPresent(transaction::setSecondMessage);
//                }
            }

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return statementList;
    }

}
