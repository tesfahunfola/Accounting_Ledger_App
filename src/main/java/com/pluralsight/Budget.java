package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Budget {
    private static final String filePath = "Data/budgets.csv";

    // Map to hold vendor -> monthly budget
    private static Map<String, Double> budgets = new HashMap<>();

    static {
        loadBudgets();
    }

    // Load budgets from CSV
    private static void loadBudgets() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String vendor = parts[0].trim();
                    double budget = Double.parseDouble(parts[1].trim());
                    budgets.put(vendor, budget);
                }
            }
        } catch (IOException e) {
            // File might not exist yet, ignore
        }
    }

    // Save budgets to CSV
    private static void saveBudgets() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Double> entry : budgets.entrySet()) {
                writer.write(entry.getKey() + "|" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving budgets: " + e.getMessage());
        }
    }

    // Set budget for a vendor
    public static void setBudget(String vendor, double amount) {
        budgets.put(vendor, amount);
        saveBudgets();
        System.out.println("Budget set for " + vendor + ": $" + amount);
    }

    // Get budget for a vendor
    public static Double getBudget(String vendor) {
        return budgets.get(vendor);
    }

    // Get all budgets
    public static Map<String, Double> getAllBudgets() {
        return new HashMap<>(budgets);
    }

    // Check if spending exceeds budget for current month
    public static boolean checkBudgetAlert(String vendor, double newAmount) {
        Double budget = getBudget(vendor);
        if (budget == null) return false;

        double currentMonthSpending = getCurrentMonthSpending(vendor);
        double totalSpending = currentMonthSpending + Math.abs(newAmount);

        if (totalSpending > budget) {
            System.out.println("⚠️ ALERT: Spending on " + vendor + " will exceed monthly budget of $" + budget +
                    ". Current spending: $" + currentMonthSpending + ", New total: $" + totalSpending);
            return true;
        } else if (totalSpending > budget * 0.8) {
            System.out.println("🔔 WARNING: Spending on " + vendor + " is nearing monthly budget of $" + budget +
                    ". Current spending: $" + currentMonthSpending + ", Projected: $" + totalSpending);
        }
        return false;
    }

    // Calculate spending for vendor in current month
    private static double getCurrentMonthSpending(String vendor) {
        double spending = 0.0;
        LocalDate now = LocalDate.now();
        try (BufferedReader reader = new BufferedReader(new FileReader("Data/transactions.csv"))) {
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
