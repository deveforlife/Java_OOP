package asm02;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String customerId;

    public User() {
    }

    public User(String name, String customerId) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
