package com.pluralsight;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TransactionManager {
    static Scanner scanner = new Scanner(System.in);
    static String filePath = "Data/transactions.csv";


    public static void addTransaction(boolean isDeposit) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath, true))) {
            String option;

            do {
                // Get today's date and time
                String date = LocalDate.now().toString();
                String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

                System.out.println("\n--- " + (isDeposit ? "Add Deposit" : "Make Payment") + " ---");
                System.out.print("\nEnter Description: ");
                String description = scanner.nextLine().trim();

                System.out.print("\nEnter Vendor Name: ");
                String vendor = scanner.nextLine().trim();

                System.out.print("\nEnter Amount (0.0): ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // clear buffer
                if (!isDeposit) {
                    amount = -amount;
                    // Check budget alert
                    Budget.checkBudgetAlert(vendor, Math.abs(amount));
                }

                // Create and save transaction
                String line = String.format("%s|%s|%s|%s|%.2f\n", date, time, description, vendor, amount);
                bufferedWriter.write(line);
                bufferedWriter.flush();

                //  Confirmation message added here
                System.out.println("\n──────────────────────────────");
                System.out.printf("💰 %-8s : $%.2f%n🏷️ Vendor      : %s%n📝 Description : %s%n✅ Status      : Transaction saved successfully!%n",
                        isDeposit ? "Deposit" : "Payment",
                        Math.abs(amount),
                        vendor,
                        description);
                System.out.println("──────────────────────────────");
                System.out.print("\n➕ Add another? (X to return, any other key to continue): ");

                option = scanner.nextLine().trim().toUpperCase();

            } while (!option.equals("X"));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
