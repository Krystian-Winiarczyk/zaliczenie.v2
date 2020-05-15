package com.company;

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
                    accountManagement(bank, account);
                    new Files().saveDataToFile(bank);
                }
            }
        }
    }

    private static void accountManagement(Bank bank, Account loggedInAccount) {
        while (loggedInAccount.isLoggedIn()) {
            System.out.println("(1) Transfer");
            System.out.println("(2) Transfer To Other Bank");
            System.out.println("(3) Payment");
            System.out.println("(4) Account Details");
            System.out.println("(5) Available accounts");
            System.out.println("(0) Logout");

            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    bank.transfer(loggedInAccount, bank.getAccounts());
                    break;
                case 2:
                    bank.transferToOtherBank(loggedInAccount);
                    break;
                case 3:
                    bank.paymentOnAccount(loggedInAccount);
                    break;
                case 4:
                    System.out.println(loggedInAccount.toString());
                    break;
                case 5:
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
