package com.pluralsight;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class TransactionApp {
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        showHomeScreen();

    }
    public static void showHomeScreen(){
        String option;
        do {
            System.out.println("""
                    ==✨🏡 Home Screen 🏠✨==
                    💵💚 D) Add Deposits 💰✅
                    💳⚡ P) Make Payment 💸💨
                    🧮 B) Balance Tracking 🧮
                    📘🧮 L) Ledger 📊📒
                    🚀 X) Exit 🌟👋
                    """);
            System.out.print("👉 Enter your choice: ");
            option = scanner.nextLine().trim().toUpperCase();
            try {
                System.out.println("🔄 Loading data, please wait...");
                Thread.sleep(5000); // pauses the program for 8 second (8000 milliseconds)
            } catch (InterruptedException e) {

                System.out.println("⚠️ Process was interrupted: " + e.getMessage());
            }

            switch (option){
                case "D":
                    TransactionManager.addTransaction(true);
                    break;
                case "P":
                    TransactionManager.addTransaction(false);
                    break;
                case "B":
                    BalanceTracker.showBalanceSummary();
                    break;
                case "L":
                    displayLedger();
                    break;
                case "X":
                    System.out.println("Exiting the app. Bye!👋");
                    return;

                default:
                    System.out.println("That's not an option\n\n");
                    break;

            }

        }while (!option.equalsIgnoreCase("X"));
    }
    //                    -------- display ledger Menu --------
    public static void displayLedger(){
        String option;
        do {
            System.out.println("""
                         --------💼 Ledger Menu --------
                         📋 A) Display All Transactions
                         💵 D) Display Only Deposits
                         💳 P) Display Only Payments
                         📊 R) Reports
                         🏠 H) Go Back Home
                    """);
            System.out.print("👉 Enter your choice: ");
            option = scanner.nextLine().trim().toUpperCase();
            switch (option){
                case "A":
                    Ledger.displayAllTransactions();
                    break;
                case "D":
                    Ledger.displayFiltered(true);
                    break;
                case "P":
                    Ledger.displayFiltered(false);
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
    //                    -------- display Report Menu --------
public static void displayReportMenu(){

        String option;

        do {
            System.out.println("""
                   1️⃣) Month to Date 🗓️
                   2️⃣) Previous Month 📅
                   3️⃣) Year to Date 📆
                   4️⃣) Previous Year 🕰️
                   5️⃣) Search by Vendor 🧾
                   6️⃣) Custom Search 🔍
                   0️⃣) Back 🔙
                    """);
            System.out.print("👉 Enter your choice: ");
            option = scanner.nextLine();
            switch (option){
                case "1":
                    Reports.displayReport(1);

                    break;
                case "2":
                    Reports.displayReport(2);
                    break;
                case "3":
                    Reports.displayReport(3);
                    break;
                case "4":
                    Reports.displayReport(4);
                    break;
                case "5":
                    Reports.searchByVendor();
                    break;
                case "6":
                    Reports.customSearch();
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