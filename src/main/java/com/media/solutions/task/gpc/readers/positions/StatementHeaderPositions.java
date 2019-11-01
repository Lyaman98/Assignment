package com.media.solutions.task.gpc.readers.positions;

public class StatementHeaderPositions {

    //index positions for parsing a StatementHeader object

    public static final int ACCOUNT_NUMBER_START = 3;
    public static final int ACCOUNT_NUMBER_END = 19;
    public static final int ACCOUNT_OWNER_START = 19;
    public static final int ACCOUNT_OWNER_END = 39;
    public static final int OPENING_BALANCE_DATE_START = 39;
    public static final int OPENING_BALANCE_DATE_END = 45;
    public static final int OPENING_BALANCE_START = 45;
    public static final int OPENING_BALANCE_END = 59;
    public static final int OPENING_BALANCE_SIGN = 59;
    public static final int CLOSING_BALANCE_START = 60;
    public static final int CLOSING_BALANCE_END = 74;
    public static final int CLOSING_BALANCE_SIGN = 74;
    public static final int SUM_OF_DEBIT_START = 75;
    public static final int SUM_OF_DEBIT_END = 89;
    public static final int SIGN_FOR_DEBIT = 89;
    public static final int SUM_OF_CREDIT_START = 90;
    public static final int SUM_OF_CREDIT_END = 104;
    public static final int SIGN_FOR_CREDIT = 104;
    public static final int SERIAL_NUMBER_START = 105;
    public static final int SERIAL_NUMBER_END = 108;
    public static final int STATEMENT_DATE_START = 108;
    public static final int STATEMENT_DATE_END = 114;




}
