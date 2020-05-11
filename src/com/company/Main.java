package com.company;

import com.company.models.Account;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Bank bank = new Bank(null);
        boolean bankSelection = true;

        while (bankSelection) {
            boolean authSelection = true;

            System.out.println("Select Bank: ");
            System.out.println("(1) Ing");
            System.out.println("(2) Millennium");
            System.out.println("(3) Santander");
            System.out.println("(4) Pko");
            System.out.println("(0) Exit");

            switch (new Scanner(System.in).nextInt()) {
                case 1: bank = new Bank(BankNames.ING);
                    break;
                case 2: bank = new Bank(BankNames.MILLENNIUM);
                    break;
                case 3: bank = new Bank(BankNames.SANTANDER);
                    break;
                case 4: bank = new Bank(BankNames.PKO);
                    break;
                case 0: bankSelection = false;
                    break;
                default: System.out.println("wtf");
            }

            loadAccountsFromFile(bank);
            while (authSelection) {
                System.out.println("(1) Create New Account");
                if (bank.getAccounts().size() != 0) System.out.println("(2) Login");
                System.out.println("(0) Back");

                Account account = switch (new Scanner(System.in).nextInt()) {
                    case 1 -> bank.createNewBankAccount();
                    case 2 -> bank.login();
                    case 0 -> {
                        authSelection = false;
                        yield null;
                    }
                    default -> null;
                };

                if (account != null) {
                    accountMenagment(bank, account);
                    saveBankData(bank);
                }
            }
        }
    }

    private static void saveBankData(Bank selectedBank) {
        System.out.println("Zapis");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(selectedBank.getName() + ".txt", true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(selectedBank.getAccounts());

            fileOutputStream.close();
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

    private static boolean loadAccountsFromFile(Bank selectedBank) {
        System.out.println("Wczytanie");
        try {
            FileInputStream fileInputStream = new FileInputStream(selectedBank.getName() + ".txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Deque<Account> accounts = (Deque<Account>) objectInputStream.readObject();
            selectedBank.setAccounts(accounts);
            return true;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    private static void accountMenagment(Bank bank, Account loggedInAccount) {
        while (loggedInAccount.isLoggedIn()) {
            System.out.println("(1) Transfer");
            System.out.println("(2) Account Details");
            System.out.println("(3) Available accounts");
            System.out.println("(0) Logout");

            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    transferCreator(bank, loggedInAccount);
                    break;
                case 2:
                    System.out.println(loggedInAccount.toString());
                    break;
                case 3:
                    bank.printAccounts();
                    break;
                case 0:
                    bank.logout(loggedInAccount);
                    break;
                default:
                    System.out.println("Error !");
                    return;
            }
        }
    }

    private static void transferCreator(Bank bank, Account currentAccount) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Account number to transfer");
        String number = sc.next();
        System.out.println("Transaction Amount");
        Double amount = sc.nextDouble();

        bank.transfer(currentAccount, number, amount);
    }
}
