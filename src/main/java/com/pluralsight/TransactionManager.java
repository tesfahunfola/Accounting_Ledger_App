package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {

    public static List<Transaction> loadTransactionsFromFile(String filePath){
        List<Transaction> transactions = new ArrayList<>();

        DateTimeFormatter date = DateTimeFormatter.ISO_LOCAL_DATE; //yyyy/MM/dd
        DateTimeFormatter time = DateTimeFormatter.ofLocalizedTime(FormatStyle.valueOf("HH:mm:ss"));
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            bufferedReader.readLine();

            String transactionString;
            while ((transactionString = bufferedReader.readLine())!= null){

                String[] transactionArr = transactionString.split("\\|");
                Transaction transaction = new Transaction(LocalDate.parse(transactionArr[0],date),LocalTime.parse(transactionArr[1], time), transactionArr[2], transactionArr[3], Double.parseDouble(transactionArr[4]));

                transactions.add(transaction);
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return transactions;
    }
    public static void displayTransactions(List<Transaction> transactions){
        for (Transaction transaction : transactions){
            System.out.println(transaction);
        }
    }

}
