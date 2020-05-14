package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Deque;

public class Files {
    public Files() {}
    public void saveDataToFile(Bank selectedBank) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(selectedBank.getName() + ".txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(selectedBank.getAccounts());

            fileOutputStream.close();
            objectOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadDataFromFile(Bank selectedBank) {
        try {
            FileInputStream fileInputStream = new FileInputStream(selectedBank.getName() + ".txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Deque<Account> accounts = (Deque<Account>) objectInputStream.readObject();
            selectedBank.setAccounts(accounts);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
