package com.company.models;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;
import java.util.Scanner;

public class Bank {
    private Scanner sc = new Scanner(System.in);

    private String name;
    private Deque<Account> accounts = new ArrayDeque<>();

    public Bank(String name) {
        this.name = name;
    }

    public void createNewBankAccount(Person owner, String password, String login) {
        Account newAccount = new Account(owner, login, password);
        this.accounts.add(newAccount);
    }

    public Account login() {

        System.out.println("Login: ");
        String login = this.sc.next();
        System.out.println("Password: ");
        String password = this.sc.next();

        Optional<Account> findedAccount = this.accounts.stream()
                .filter(account1 -> account1.getLogin().equals(login) && account1.getPassword().equals(password))
                .findFirst();

        Account account = findedAccount.orElseThrow();
        account.setLoggedIn(true);

        return account;
    }

    public void printAccounts() {
        this.accounts.forEach(account -> {
            System.out.println(account.toString());
        });
    }

    public void logout(Account account) {
        account.setLoggedIn(false);
        System.out.println("Account logged out");
    }

    public void transfer(Account transferFrom, String numberToTransfer, Double amount) {
        if ((transferFrom.getBalance() - amount) < 0) {
            System.out.println("Lack of account funds");
        } else {
            Optional<Account> didAccountExist = this.accounts.stream()
                    .filter(account -> account.getAccountNumber().equals(numberToTransfer))
                    .findFirst();

            if (didAccountExist.isPresent()) {
                Account accountToTransfer = didAccountExist.get();
                transferFrom.setBalance(transferFrom.getBalance() - amount);
                accountToTransfer.setBalance(accountToTransfer.getBalance() + amount);
            } else {
                System.out.println("Account to transfer didn't exist");
            }
        }
    }
}
