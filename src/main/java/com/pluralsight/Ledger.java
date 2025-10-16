package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Ledger {

    static Scanner scanner = new Scanner(System.in);
    static String filePath = "Data/transactions.csv";

    //    display all transaction
    public static void displayAllTransactions(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

//    display only deposits or only payments
    public static void displayFiltered(boolean displayDeposits){
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine())!= null){
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                double amount = Double.parseDouble(parts[4]);

                if ((displayDeposits && amount > 0) || (!displayDeposits && amount < 0)) {
                    System.out.println(line);
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}
