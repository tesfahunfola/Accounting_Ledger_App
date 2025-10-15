package com.pluralsight;

import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class TransactionApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showHomeScreen();

    }
    public static void showHomeScreen(){
        String option;
        do {
            System.out.println("""
                    ==🏠 Home Screen==
                    💰 D) Add Deposit\s
                    💸 P) Make Payment 🧾
                    📄 L) Ledger 📒
                    ❌ X) Exit 👋
                    👉 Enter your choice:
                    """);
            option = scanner.nextLine().trim().toUpperCase();

            switch (option){
                case "D":
                    TransactionManager.addTransaction(true);
                    break;
                case "P":
                    TransactionManager.addTransaction(false);
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    System.out.println("Exiting the app. Bye!👋");
                    return;
                default:
                    System.out.println("That's not an option.");
                    break;

            }
        }while (!option.equalsIgnoreCase("X"));
    }
    public static void displayLedger(){
        String option;
        do {
            System.out.println("""
                    \n --------Ledger Menu--------
                    A) Display All Transactions
                    D) Display only Deposits
                    P) Display only Payments
                    R) Reports
                    H) Go Back Home
                    
                    👉 Enter your choice:
                    """);
            option = scanner.nextLine().trim().toUpperCase();
            switch (option){
                case "A":
                    TransactionService.displayAllTransactions();
                    break;
                case "D":
                    TransactionService.displayFiltered(true);
                    break;
                case "P":
                    TransactionService.displayFiltered(false);
                    break;
                case "R":
                    displayReportMenu();
                    break;
                case "H":
                    System.out.println("Returning to home menu...");
                    return;
                default:
                    System.out.println("That's not option.");
            }

        }while (!option.equalsIgnoreCase("H"));
    }
public static void displayReportMenu(){

        String option;

        do {
            System.out.println("""
                    1) Month to Date
                    2) Previous Month
                    3) Year to Date
                    4) Previous Year
                    5) Search by Vendor
                    6) Custom Search
                    0) Back
                    """);
            option = scanner.nextLine();
            switch (option){
                case "1":
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "0":
                    System.out.println("Back to Ledger");
                    return;
                default:
                    System.out.println("That's not option.");


            }
        }while (!option.equals("0"));
}

}