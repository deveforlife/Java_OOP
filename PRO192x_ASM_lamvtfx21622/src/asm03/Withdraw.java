package asm03;

public interface Withdraw {

    boolean deposit(double amount);
    boolean withdraw(double amount);
    boolean isAccepted(double amount);
}
