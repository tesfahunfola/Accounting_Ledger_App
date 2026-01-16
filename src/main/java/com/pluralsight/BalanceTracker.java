package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

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

            // Display budget status
            displayBudgetStatus();

            System.out.println("======================================\n");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid number format in transactions file.");
        }
    }

    private static void displayBudgetStatus() {
        var budgets = Budget.getAllBudgets();
        if (budgets.isEmpty()) return;

        System.out.println("\n💰 Budget Status (Current Month):");
        for (var entry : budgets.entrySet()) {
            String vendor = entry.getKey();
            double budget = entry.getValue();
            double spent = getCurrentMonthSpendingForVendor(vendor);
            double remaining = budget - spent;
            System.out.printf("  %s: Spent $%.2f / $%.2f (Remaining: $%.2f)%n",
                    vendor, spent, budget, remaining);
            if (spent > budget) {
                System.out.printf("    ⚠️ Over budget by $%.2f!%n", spent - budget);
            } else if (spent > budget * 0.8) {
                System.out.println("    🔔 Nearing budget limit.");
            }
        }
    }

    private static double getCurrentMonthSpendingForVendor(String vendor) {
        double spending = 0.0;
        LocalDate now = LocalDate.now();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                LocalDate date = LocalDate.parse(parts[0]);
                if (date.getMonth() == now.getMonth() && date.getYear() == now.getYear()) {
                    String v = parts[3].trim();
                    double amount = Double.parseDouble(parts[4].trim());
                    if (v.equalsIgnoreCase(vendor) && amount < 0) {
                        spending += Math.abs(amount);
                    }
                }
            }
        } catch (IOException e) {
            // Ignore
        }
        return spending;
    }
}
