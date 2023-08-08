package asm04;

import asm02.Account;
import asm02.Customer;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountDao extends Account implements Serializable {

    private final static String FILE_PATH = "store\\accounts.dat";

    public AccountDao() {

    }

    //update số dư tài khoản
    public static void update(Account editAccount){
        List<Account> accounts = list();
        boolean hasExit = accounts.stream().anyMatch(account -> account.getAccountNumber()
                .equals(editAccount.getAccountNumber()));

        List<Account> updateAccounts;
        if (!hasExit){
            updateAccounts = new ArrayList<>(accounts);
            updateAccounts.add(editAccount);
        }
        else {
            updateAccounts = new ArrayList<>();
            for (Account account : accounts){
                if (account.getAccountNumber().equals(editAccount.getAccountNumber())){
                    updateAccounts.add(editAccount);
                }
                else {
                    updateAccounts.add(account);
                }
            }
        }

        try {
            save(updateAccounts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void save(List<Account> accounts) throws IOException {
        //TextFileService.writeFile(FILE_PATH, accounts);
    }

    public static List<Account> list(){
        return TextFileService.readFile(FILE_PATH);
    }


}
