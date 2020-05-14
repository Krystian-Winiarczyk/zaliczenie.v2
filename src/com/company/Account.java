package com.company.models;

import java.io.Serializable;

public class Account implements Serializable {
    private String accountNumber;
    private Person owner;
    private Double balance;
    private boolean isLoggedIn;
    private String login, password;

    public Account(Person owner, String login, String password) {
        this.owner = owner;
        this.login = login;
        this.password = password;

        this.accountNumber = String.valueOf(Math.round(Math.random() * 1000000));
        this.isLoggedIn = false;
        this.balance = 100.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", owner=" + owner.toString() +
                ", balance=" + balance +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
