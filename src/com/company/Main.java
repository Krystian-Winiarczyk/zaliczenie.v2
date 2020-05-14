package com.company;

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
                case 0:
                    bankSelection = false;
                    authSelection = false;
                    break;
                default:
                    authSelection = false;
                    System.out.println("Error ");
            }

            while (authSelection) {
                new Files().loadDataFromFile(bank);
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
                    new Files().saveDataToFile(bank);
                }
            }
        }
    }

//    private static void saveDataToFile(Bank selectedBank) {
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(selectedBank.getName() + ".txt");
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(selectedBank.getAccounts());
//
//            fileOutputStream.close();
//            objectOutputStream.close();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private static void loadDataFromFile(Bank selectedBank) {
//        try {
//            FileInputStream fileInputStream = new FileInputStream(selectedBank.getName() + ".txt");
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            Deque<Account> accounts = (Deque<Account>) objectInputStream.readObject();
//            selectedBank.setAccounts(accounts);
//        } catch (Exception e) {
//            System.out.println(e.getStackTrace());
//        }
//    }

    private static void accountMenagment(Bank bank, Account loggedInAccount) {
        while (loggedInAccount.isLoggedIn()) {
            System.out.println("(1) Transfer");
            System.out.println("(2) Transfer To Other Bank");
            System.out.println("(3) Account Details");
            System.out.println("(4) Available accounts");
            System.out.println("(0) Logout");

            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    bank.transfer(loggedInAccount, bank.getAccounts());
                    break;
                case 2: bank.transferToOtherBank(loggedInAccount);
                    break;
                case 3:
                    System.out.println(loggedInAccount.toString());
                    break;
                case 4:
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
}
