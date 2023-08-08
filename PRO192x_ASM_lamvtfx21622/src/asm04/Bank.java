package asm04;

import asm02.Customer;

import java.util.ArrayList;
import java.util.UUID;

public class Bank extends asm02.Bank {
    private String id;
    public Bank() {
        this.id = String.valueOf(UUID.randomUUID());
    }

}
