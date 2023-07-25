package asm04;

import asm02.Customer;

import java.io.Serializable;

public class CustomerDao extends Customer implements Serializable {
    public CustomerDao() {

    }

    @Override
    public String toString() {
        return getCustomerId() + "," + getName();
    }
}
