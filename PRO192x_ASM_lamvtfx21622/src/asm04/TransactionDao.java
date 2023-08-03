package asm04;

import asm02.Account;
import asm03.Transaction;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class TransactionDao extends Transaction implements Serializable {

    private final static String FILE_PATH = "store\\transactions.dat";

    public TransactionDao() {

    }

    public static void save(List<Transaction> transactions) throws IOException {
        TextFileService.writeFile(FILE_PATH, transactions);
    }

    public static List<Transaction> list(){
        return TextFileService.readFile(FILE_PATH);
    }


}
