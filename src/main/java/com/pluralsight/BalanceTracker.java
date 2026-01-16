package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BalanceTracker {

    private static final String filePath = "Data/transactions.csv";

    //   to display balance summary from deposit and payment
    public static void showBalanceSummary() {
        double totalDeposits = 0.0;
        double totalPayments = 0.0;
        double balance = 0.0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                double amount = Double.parseDouble(parts[4].trim());

                if (amount > 0) totalDeposits += amount;
                else totalPayments += Math.abs(amount);
            }

            balance = totalDeposits - totalPayments;

            // 🧾 Display the result summary

            System.out.println("\n🧮 ===== Balance Tracking Summary =====");
            System.out.printf("💰 Total Deposits:  $%.2f%n", totalDeposits);
            System.out.printf("💸 Total Payments:  $%.2f%n", totalPayments);
            System.out.println("--------------------------------------");
            System.out.printf("🏦 Current Balance: $%.2f%n", balance);
            System.out.println("======================================\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid number format in transactions file.");
        }
    }
}
