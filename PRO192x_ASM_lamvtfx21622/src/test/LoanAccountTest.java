package test;

import asm03.LoanAccount;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanAccountTest {
    @BeforeAll
    static void beforeAll(){
        LoanAccount loanAccount = new LoanAccount();
        loanAccount.setBalance(50000000);

    }

    @Test
    void withdraw() {
    }

    @org.junit.jupiter.api.Test
    void isAccepted() {
        LoanAccount loanAccount = new LoanAccount();
        boolean check = loanAccount.isAccepted(500000);
        assertEquals(true, check);
    }

    @org.junit.jupiter.api.Test
    void isAccepted1() {
        LoanAccount loanAccount = new LoanAccount();
        boolean check = loanAccount.isAccepted(500000);
        assertEquals(true, check);
    }

    @AfterAll
    static void afterAll(){

    }
}