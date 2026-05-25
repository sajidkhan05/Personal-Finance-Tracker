# 💰 Personal Finance Tracker

A command-line based Personal Finance Tracker built in **Java** using
Object-Oriented Programming (OOP) principles.

## 🏆 Certificate
Completed as part of **TCS iON Applied Industry Projects (AIP)** program.
- 📅 Duration: 27 March 2026 – 13 May 2026
- ⏱️ Total Hours: 90 Hours
- 🔖 Cert. ID: 1190-29553944-1016

## ✅ Features
- Add Income & Expense transactions with category, date & description
- Real-time account balance tracking
- Overspending protection (blocks expense if balance is insufficient)
- Category-wise spending breakdown
- Monthly financial report generation
- Complete transaction history view

## 🧩 OOP Concepts Used
| Concept | Where Applied |
|---|---|
| Encapsulation | Private fields in Transaction & Account classes |
| Abstraction | Abstract Transaction class + ReportGenerator |
| Inheritance | Income & Expense extend Transaction |
| Polymorphism | getSignedAmount() & addTransaction() |

## 🗂️ Project Structure
PersonalFinanceTracker/
├── Transaction.java       → Abstract base class
├── Income.java            → Derived class (adds to balance)
├── Expense.java           → Derived class (deducts from balance)
├── Account.java           → Manages balance & transaction history
├── ReportGenerator.java   → Generates financial reports
└── Main.java              → CLI menu & entry point

## 🛠️ Tech Stack
- **Language:** Java (JDK 17+)
- **Paradigm:** Object-Oriented Programming (OOP)
- **Interface:** Command-Line (CLI)
- **Libraries:** Java Collections API, Java Scanner
- **Version Control:** Git & GitHub

## ▶️ How to Run
**Step 1 — Compile:**
```bash
javac -d out Transaction.java Income.java Expense.java Account.java ReportGenerator.java Main.java
```
**Step 2 — Run:**
```bash
java -cp out finance.Main
```

## 📋 Sample Menu
┌─────────────────────────────────────┐
│           MAIN MENU                 │
├─────────────────────────────────────┤
│  1. Add Income                      │
│  2. Add Expense                     │
│  3. View Summary Report             │
│  4. View Transaction History        │
│  5. View Category-wise Report       │
│  6. View Monthly Report             │
│  7. Check Current Balance           │
│  0. Exit                            │
└─────────────────────────────────────┘

## 👨‍💻 Author
**Sajid Khan**
