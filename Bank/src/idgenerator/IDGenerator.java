package idgenerator;

import java.lang.Math;

public class IDGenerator {
    private static int id = 0, bankId = 0, transactionId = 0;

    public static int generateDebitId() {
        id++;
        int idLen = String.valueOf(id).length();
        return (int)Math.pow(10, idLen) + id;
    }

    public static int generateCreditId() {
        id++;
        int idLen = String.valueOf(id).length();
        return (int)Math.pow(10, idLen) * 2 + id;
    }

    public static int generateDepositId() {
        id++;
        int idLen = String.valueOf(id).length();
        return (int)Math.pow(10, idLen) * 3 + id;
    }

    public static int generateBankId() {
        bankId++;
        return bankId;
    }

    public static int generateTransactionId() {
        transactionId++;
        return transactionId;
    }
}
