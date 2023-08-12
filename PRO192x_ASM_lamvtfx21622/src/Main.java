import asm02.Account;
import asm02.MenuAs2;
import asm03.MenuAs3;
import asm03.Transaction;
import asm04.AccountDao;
import asm04.MenuAs4;
import asm04.TransactionDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
//        MenuAs2 menu02 = new MenuAs2();
//        menu02.showMenu();

//        MenuAs3 menu03 = new MenuAs3();
//        menu03.showMenu();

        MenuAs4 menu04 = new MenuAs4();
        menu04.showMenu();

//        String accNum =  "794562";
//
//        Account newacount = new Account();
//
//        List<Transaction> a = newacount.getTransactions(accNum);
//        for (Transaction transaction : a){
//            System.out.println(transaction.getAccountNumber());
//            System.out.println(transaction.getAmount());
//            System.out.println(transaction.getType());
//            System.out.println(transaction.getDateTime());
//        }


    }
}