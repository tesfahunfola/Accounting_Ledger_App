package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reports {
    static Scanner scanner = new Scanner(System.in);
    static String filePath = "Data/transactions.csv";


    public static void displayReport(int reportType) {
        LocalDate todayDate = LocalDate.now();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            bufferedReader.readLine();
            String reportString;

            while ((reportString = bufferedReader.readLine()) != null) {
                if (reportString.isBlank()) continue;
                String[] parts = reportString.split("\\|");
                if (parts.length < 5) continue;

                LocalDate reportDate = LocalDate.parse(parts[0]);

                switch (reportType) {
                    // Month to Date
                    case 1:
                        if (reportDate.getMonth() == todayDate.getMonth() &&
                                reportDate.getYear() == todayDate.getYear())
                            System.out.println(reportString);
                        break;

                    // Previous Month
                    case 2:
                        LocalDate prevMonth = todayDate.minusMonths(1);
                        if (reportDate.getMonth() == prevMonth.getMonth() &&
                                reportDate.getYear() == prevMonth.getYear())
                            System.out.println(reportString);
                        break;

                    // Year to Date
                    case 3:
                        LocalDate firstDay = todayDate.withDayOfYear(1);
                        if ((reportDate.isEqual(firstDay) || reportDate.isAfter(firstDay)) &&
                                reportDate.isBefore(todayDate.plusDays(1)))
                            System.out.println(reportString);
                        break;

                    // Previous Year
                    case 4:
                        int prevYear = todayDate.getYear() - 1;
                        if (reportDate.getYear() == prevYear)
                            System.out.println(reportString);
                        break;

                    default:
                        System.out.println("Invalid report type");
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    public static void searchByVendor() {
        System.out.print("Enter vendor: ");
        String vendorQuery = scanner.nextLine().trim().toLowerCase();
        if (vendorQuery.isEmpty()){
            System.out.println("No vendor entered.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine();
            String line;
            boolean found = false;

            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                String vendor = parts[3].trim().toLowerCase();
                if (vendor.contains(vendorQuery)) {
                    System.out.println(line);
                    found = true;
                }
            }
            if (!found) System.out.println("No transactions found for vendor: " + vendorQuery);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public static void customSearch() {
        System.out.println("Custom Search 🔍");
        System.out.print("Start Date (YYYY-MM-DD): ");
        LocalDate start = parseDate(scanner.nextLine().trim());

        System.out.print("End Date (YYYY-MM-DD): ");
        LocalDate end = parseDate(scanner.nextLine().trim());

        System.out.print("Description: ");
        String description = scanner.nextLine().trim();

        System.out.print("Vendor: ");
        String vendor = scanner.nextLine().trim();

        System.out.print("Amount: ");
        Double amount = parseAmount(scanner.nextLine().trim());

        int found = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            br.readLine();

            for (String line; (line = br.readLine())!= null;){
                if (line.isBlank()) continue;

                String[] p = line.split("\\|");
                if (p.length < 5) continue;

                LocalDate date = LocalDate.parse(p[0].trim());
                String d = p[2].toLowerCase();
                String v = p[3].toLowerCase();
                double amnt = Double.parseDouble(p[4].trim());

                if ((start != null && date.isBefore(start)) || (end != null && date.isAfter(end)) ||(!description.isEmpty() && !d.contains(description)) || (!vendor.isEmpty() && !v.contains(vendor)) || (amount != null && Double.compare(amnt, amount) != 0)) continue;

                System.out.println(line);
                found++;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (found == 0) System.out.println("No matching transaction.");

    }
    private static LocalDate parseDate(String input){
        try {return input.isBlank() ? null : LocalDate.parse(input);}
        catch (Exception e){
            System.out.println("Invalid Date"); return null;}
    }
    private static Double parseAmount(String input) {
        try { return input.isBlank() ? null : Double.parseDouble(input); }
        catch (Exception e) { System.out.println("Invalid amount."); return null; }
    }

}
