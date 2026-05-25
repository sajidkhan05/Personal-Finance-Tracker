package finance;

import java.util.Scanner;

/**
 * Command-line interface for the Personal Finance Tracker.
 *
 * OOP Concepts Applied:
 *  - ABSTRACTION  : User interacts via simple menu; all logic is hidden
 *  - ENCAPSULATION: All data manipulation goes through Account's public API
 *  - INHERITANCE  : Income / Expense objects created based on user choice
 *  - POLYMORPHISM : addTransaction() works uniformly for Income & Expense
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static Account         account;
    private static ReportGenerator reportGen;

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║   PERSONAL FINANCE TRACKER  (Java)   ║");
        System.out.println("╚══════════════════════════════════════╝");

        // ── Account Setup ─────────────────────────────────────────────
        System.out.print("\n  Enter your name          : ");
        String name = scanner.nextLine().trim();

        double initBalance = 0;
        while (true) {
            System.out.print(" Enter initial balance (Rs): ");
            try {
                initBalance = Double.parseDouble(scanner.nextLine().trim());
                if (initBalance < 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Please enter a valid non-negative number.");
            }
        }

        account   = new Account(name, initBalance);
        reportGen = new ReportGenerator(account);
        System.out.println("\n  Account created for: " + name
            + "  |  Opening Balance: Rs." + String.format("%.2f", initBalance));

        // ── Main Menu Loop ────────────────────────────────────────────
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("  Your choice: ");

            switch (choice) {
                case 1 -> addTransaction("INCOME");
                case 2 -> addTransaction("EXPENSE");
                case 3 -> reportGen.generateSummaryReport();
                case 4 -> reportGen.generateTransactionHistory();
                case 5 -> reportGen.generateCategoryReport();
                case 6 -> {
                    System.out.print("  Enter month (MM)   : ");
                    String m = scanner.nextLine().trim();
                    System.out.print("  Enter year  (YYYY) : ");
                    String y = scanner.nextLine().trim();
                    reportGen.generateMonthlyReport(m, y);
                }
                case 7 -> {
                    System.out.printf("%n  Current Balance: Rs. %.2f%n",
                        account.getBalance());
                }
                case 0 -> {
                    System.out.println("\n  Thank you for using Personal Finance Tracker. Goodbye!\n");
                    running = false;
                }
                default -> System.out.println("  [ERROR] Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    // ── Add Income or Expense ─────────────────────────────────────────
    private static void addTransaction(String type) {
        System.out.println("\n  ── Add " + type + " ──────────────────────────────");

        double amount = 0;
        while (true) {
            System.out.print("  Amount (Rs.)    : ");
            try {
                amount = Double.parseDouble(scanner.nextLine().trim());
                if (amount <= 0) throw new NumberFormatException();
                break;
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Enter a positive number.");
            }
        }

        System.out.print("  Category        : ");
        String category = scanner.nextLine().trim();

        System.out.print("  Date (DD-MM-YYYY): ");
        String date = scanner.nextLine().trim();

        try {
            Transaction t;
            if (type.equals("INCOME")) {
                t = new Income(amount, category, date);
            } else {
                t = new Expense(amount, category, date);
            }
            account.addTransaction(t);   // Polymorphism: works for both types
        } catch (IllegalArgumentException e) {
            System.out.println("  [ERROR] " + e.getMessage());
        }
    }

    // ── Menu Display ──────────────────────────────────────────────────
    private static void printMenu() {
        System.out.println("\n┌─────────────────────────────────────┐");
        System.out.println("│           MAIN MENU                 │");
        System.out.println("├─────────────────────────────────────┤");
        System.out.println("│  1. Add Income                      │");
        System.out.println("│  2. Add Expense                     │");
        System.out.println("│  3. View Summary Report             │");
        System.out.println("│  4. View Transaction History        │");
        System.out.println("│  5. View Category-wise Report       │");
        System.out.println("│  6. View Monthly Report             │");
        System.out.println("│  7. Check Current Balance           │");
        System.out.println("│  0. Exit                            │");
        System.out.println("└─────────────────────────────────────┘");
    }

    // ── Safe Integer Input ────────────────────────────────────────────
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [ERROR] Please enter a valid number.");
            }
        }
    }
}