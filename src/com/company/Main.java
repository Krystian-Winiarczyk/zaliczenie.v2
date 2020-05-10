package com.company;

import com.company.models.Account;
import com.company.models.Person;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Bank bank = new Bank(null);
        System.out.println("Select Bank: ");
        System.out.println("(1) Ing");
        System.out.println("(2) Millennium");
        System.out.println("(3) Santander");
        System.out.println("(4) Pko");

        switch (new Scanner(System.in).nextInt()) {
            case 1: bank = new Bank(BankNames.ING);
            break;
            case 2: bank = new Bank(BankNames.MILLENNIUM);
            break;
            case 3: bank = new Bank(BankNames.SANTANDER);
            break;
            case 4: bank = new Bank(BankNames.PKO);
            break;
            default: System.out.println("wtf");
        }

        if (!loadAccountsFromFile(bank)) return;

//        bank.createNewBankAccount(new Person("Adam", "Nowak", "pwkk12@wp.pl"), "sara1234", "adamiak321");
//        bank.createNewBankAccount(new Person("Kamil", "Stasica", "mewa12@gmail.com"), "mewaa12", "pwkk12");
//        bank.createNewBankAccount(new Person("Dariusz", "Robercik", "robson2@wp.pl"), "rosbon300", "ramen003");

        Account loggedInAccount = bank.login();

        while (loggedInAccount.isLoggedIn()) {
            System.out.println("(1) Transfer");
            System.out.println("(2) Account Details");
            System.out.println("(3) Available accounts");
            System.out.println("(4) Logout");

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
                case 4:
                    bank.logout(loggedInAccount);
                    break;
                default:
                    System.out.println("Error !");
                    return;
            }
        }
//        saveBankData(bank);
    }

    private static void saveBankData(Bank selectedBank) {
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


    private static void transferCreator(Bank bank, Account currentAccount) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Account number to transfer");
        String number = sc.next();
        System.out.println("Transaction Amount");
        Double amount = sc.nextDouble();

        bank.transfer(currentAccount, number, amount);
    }
}
