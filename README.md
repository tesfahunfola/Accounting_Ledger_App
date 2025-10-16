# 🧾 Accounting Ledger Application

> 🎓 *Pluralsight Java Developer Academy — Capstone Project 1*  
> CLI-based financial ledger that tracks deposits, payments, and generates reports from a CSV file.

---

## 📚 Table of Contents
- [📘 Overview](#-overview)
- [⚙️ Features](#️-features)
- [🧠 Project Structure](#-project-structure)
- [🚀 How to Run](#-how-to-run)
- [💻 Usage Guide](#-usage-guide)
- [📊 Report Types](#-report-types)
- [✨ Custom Search](#-custom-search)
- [🧩 Interesting Code Snippet](#-interesting-code-snippet)
- [📸 Screenshots](#-screenshots)
- [📂 File Format](#-file-format)
- [🤝 Credits](#-credits)

---

## 📘 Overview

The **Accounting Ledger Application** is a simple yet powerful **Java command-line tool** that helps users track financial transactions — both personal and business.

It allows users to:
- Add **Deposits** 💰  
- Record **Payments** 💸  
- View a complete **Ledger** 📒  
- Generate insightful **Reports** 📊  
- Perform flexible **Custom Searches** 🔍  

All transactions are saved and loaded automatically from a CSV file (`transactions.csv`).

---

## ⚙️ Features

✅ **Home Menu** — Intuitive options to add deposits, payments, view ledger, or exit.  
✅ **Ledger View** — Filter by *All*, *Deposits*, or *Payments*.  
✅ **Reports** — Generate Month-to-Date, Previous Month, Year-to-Date, and Previous Year reports.  
✅ **Vendor Search** — Find all transactions by vendor name.  
✅ **Custom Search** — Filter by date range, description, vendor, or amount.  
✅ **Persistent Storage** — Reads and writes to `transactions.csv`.  
✅ **Polished Console UI** — Includes emojis, clear borders, and confirmation messages.  
✅ **Error Handling** — Robust parsing and validation for file input/output.  

  

---

## 🧠 Project Structure



```bash
AccountingLedger/
├── Data/
│ └── transactions.csv
├── src/com/pluralsight/
│ ├── Transaction.java
│ ├── TransactionManager.java
│ ├── TransactionService.java
│ ├── Reports.java
│ └── TransactionApp.java
└── README.md
```




**Main entry point:** `TransactionApp.java`

---

## 🚀 How to Run

### Prerequisites
- Java 17+ installed  
- Terminal or IDE (IntelliJ / VS Code / Eclipse)

### Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/accounting-ledger.git
   cd accounting-ledger

2. Ensure a folder Data/ exists with:

```bash
date|time|description|vendor|amount
```
3. Compile and run:
```bash
javac -d out src/com/pluralsight/*.java
java -cp out com.pluralsight.TransactionApp

```

## 💻 Usage Guide
### 🏠 Home Menu

```
==✨🏡 Home Screen 🏠✨==
💵 D) Add Deposit
💳 P) Make Payment
📘 L) Ledger
🚀 X) Exit
👉 Enter your choice:
```

### 💰 Add Deposit

Example:

```
Enter Description: Client Invoice
Enter Vendor Name: Acme Corp
Enter Amount (0.0): 1500
──────────────────────────────
💰 Deposit   : $1500.00
🏷️ Vendor      : Acme Corp
📝 Description : Client Invoice
✅ Status      : Transaction saved successfully!
──────────────────────────────
```


## 📊 Report Types


| Option | Report Type      | Description                      |
| :----: | ---------------- | -------------------------------- |
|   1️⃣  | Month-to-Date    | All transactions from this month |
|   2️⃣  | Previous Month   | All from last calendar month     |
|   3️⃣  | Year-to-Date     | Transactions since January 1     |
|   4️⃣  | Previous Year    | Last year’s transactions         |
|   5️⃣  | Search by Vendor | Find entries by vendor name      |
|   6️⃣  | Custom Search    | Filter by any field              |



## ✨ Custom Search
Example prompt:
```
Start Date (YYYY-MM-DD): 2025-09-01
End Date (YYYY-MM-DD): 2025-10-01
Description contains: coffee
Vendor contains: starbucks
Amount (exact): 
```

### Result

```
2025-09-15|09:15:00|Coffee|Starbucks|-5.25
```

## 📸 Screenshots




## 📂 File Format

All transactions are stored in ```Data/transactions.csv``` in this format:

```
date|time|description|vendor|amount
2025-10-15|09:00:00|Coffee|Starbucks|-5.25
2025-10-14|10:15:00|Salary|Acme Corp|2000.00
```

## 🤝 Credits


👨‍💻 **Developed by:** *Tesfahun Fola*  
🎓 *Pluralsight Java Developer Academy*  

🧑‍🏫 **Instructor:** ***Maaike*** — thank you for the guidance, inspiration, and support throughout this capstone journey! 🙏✨  

💡 *Capstone Project 1 — Accounting Ledger CLI Application*  
📅 *Completed as part of the Java Development Fundamentals course*  


## 🏁 Final Notes

💡 This project demonstrates practical Java fundamentals:
file I/O, OOP, user input handling, data parsing, and modular design — wrapped in a clean, engaging CLI interface.
