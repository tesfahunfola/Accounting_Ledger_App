package com.pluralsight;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TransactionApp {
    static List<Transaction> transactions = TransactionManager.loadTransactionsFromFile("Data/transactions.csv");
    public static void main(String[] args) {



    }
}