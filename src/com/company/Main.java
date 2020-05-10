package com.company;

import com.company.models.Account;
import com.company.models.Bank;
import com.company.models.Person;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Bank ing = new Bank("Ing");

        ing.createNewBankAccount(new Person("Adam", "Nowak", "pwkk12@wp.pl"), "sara1234", "adamiak321");
        ing.createNewBankAccount(new Person("Kamil", "Stasica", "mewa12@gmail.com"), "mewaa12", "pwkk12");
        ing.createNewBankAccount(new Person("Dariusz", "Robercik", "robson2@wp.pl"), "rosbon300", "ramen003");

        Account loggedInAccount = ing.login();

        while (loggedInAccount.isLoggedIn()) {
            System.out.println("(1) Transfer");
            System.out.println("(2) Account Details");
            System.out.println("(3) Available accounts");
            System.out.println("(4) Logout");

            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    transferCreator(ing, loggedInAccount);
                    break;
                case 2:
                    System.out.println(loggedInAccount.toString());
                    break;
                case 3:
                    ing.printAccounts();
                    break;
                case 4:
                    ing.logout(loggedInAccount);
                    break;
                default:
                    System.out.println("Error !");
                    return;
            }
        }
    }

    private static void accountMenagment(Bank bank) {

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
