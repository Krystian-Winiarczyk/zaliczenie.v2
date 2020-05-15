package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Transfer {
    public Transfer(String transferFrom, String transferTo, Date transferDate, Double amount) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("transfers.txt", true));
            bufferedWriter.write("-----------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.write("Date: " + transferDate);
            bufferedWriter.newLine();
            bufferedWriter.write("Transfer From: " + transferFrom + " To: " + transferTo);
            bufferedWriter.newLine();
            bufferedWriter.write("Amount: " + amount);
            bufferedWriter.newLine();
            bufferedWriter.write("-----------------------------------");
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
