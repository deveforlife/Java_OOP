package asm04;

import asm02.User;

import java.io.Serializable;

public class User04 extends User implements Serializable {
    public User04(String name, String customerId) {
        super(name, customerId);
    }

    @Override
    public String toString() {
        return getCustomerId()+","+getName();
    }
}
