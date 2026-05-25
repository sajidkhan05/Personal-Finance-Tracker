package finance;

import java.util.*;

/**
 * Generates financial reports from an Account's transaction history.
 *
 * OOP Concepts:
 *  - ABSTRACTION  : Hides all calculation complexity from the user
 *  - POLYMORPHISM : Uses Transaction references; runtime dispatch
 *                   to Income/Expense implementations
 */
public class ReportGenerator {

    private final Account account;

    public ReportGenerator(Account account) {
        if (account == null)
            throw new IllegalArgumentException("Account cannot be null.");
        this.account = account;
    }

    // ── Full Summary Report ───────────────────────────────────────────
    public void generateSummaryReport() {
        List<Transaction> txns = account.getTransactions();

        double totalIncome  = 0;
        double totalExpense = 0;

        for (Transaction t : txns) {           // Polymorphism
            if (t instanceof Income)
                totalIncome  += t.getAmount();
            else if (t instanceof Expense)
                totalExpense += t.getAmount();
        }

        double netSavings = totalIncome - totalExpense;

        printDivider("=", 60);
        System.out.println("         PERSONAL FINANCE SUMMARY REPORT");
        System.out.println("         Account Owner : " + account.getOwnerName());
        printDivider("=", 60);
        System.out.printf("  Total Income       : Rs. %10.2f%n", totalIncome);
        System.out.printf("  Total Expenses     : Rs. %10.2f%n", totalExpense);
        System.out.printf("  Net Savings        : Rs. %10.2f%n", netSavings);
        System.out.printf("  Current Balance    : Rs. %10.2f%n", account.getBalance());
        printDivider("-", 60);

        if (netSavings >= 0)
            System.out.println("  Status: SURPLUS  ✔  You are saving money!");
        else
            System.out.println("  Status: DEFICIT  ✘  Expenses exceed income!");

        printDivider("=", 60);
    }

    // ── Category-wise Breakdown ───────────────────────────────────────
    public void generateCategoryReport() {
        List<Transaction> txns = account.getTransactions();
        if (txns.isEmpty()) {
            System.out.println("  No transactions found.");
            return;
        }

        Map<String, Double> incomeByCategory  = new TreeMap<>();
        Map<String, Double> expenseByCategory = new TreeMap<>();

        for (Transaction t : txns) {
            String cat = t.getCategory();
            if (t instanceof Income) {
                incomeByCategory.merge(cat, t.getAmount(), Double::sum);
            } else {
                expenseByCategory.merge(cat, t.getAmount(), Double::sum);
            }
        }

        printDivider("=", 60);
        System.out.println("         CATEGORY-WISE BREAKDOWN");
        printDivider("=", 60);

        System.out.println("  INCOME by Category:");
        if (incomeByCategory.isEmpty()) {
            System.out.println("    (none)");
        } else {
            incomeByCategory.forEach((cat, amt) ->
                System.out.printf("    %-18s : Rs. %9.2f%n", cat, amt));
        }

        printDivider("-", 60);
        System.out.println("  EXPENSES by Category:");
        if (expenseByCategory.isEmpty()) {
            System.out.println("    (none)");
        } else {
            expenseByCategory.forEach((cat, amt) ->
                System.out.printf("    %-18s : Rs. %9.2f%n", cat, amt));
        }
        printDivider("=", 60);
    }

    // ── All Transactions ─────────────────────────────────────────────
    public void generateTransactionHistory() {
        List<Transaction> txns = account.getTransactions();
        printDivider("=", 90);
        System.out.println("  TRANSACTION HISTORY  |  Owner: " + account.getOwnerName());
        printDivider("=", 90);

        if (txns.isEmpty()) {
            System.out.println("  No transactions recorded yet.");
        } else {
            int i = 1;
            for (Transaction t : txns) {
                System.out.printf("  #%-3d %s%n", i++, t);
            }
        }
        printDivider("=", 90);
    }

public void generateMonthlyReport(String month, String year) {
    List<Transaction> txns = account.getTransactions();

    // Normalize to remove leading zeros before comparing
    // So "05" and "5" both become "5" for safe comparison
    String normalizedMonth = month.replaceAll("^0+", "");
    String normalizedYear  = year.trim();

    printDivider("=", 80);
    System.out.println("  MONTHLY REPORT — " + String.format("%02d",
            Integer.parseInt(normalizedMonth)) + "/" + normalizedYear);
    printDivider("=", 80);

    double income = 0, expense = 0;
    boolean found = false;

    for (Transaction t : txns) {
        String[] parts = t.getDate().split("-");
        if (parts.length == 3) {
            // Normalize stored month too — handles both "5" and "05"
            String storedMonth = parts[1].replaceAll("^0+", "");
            String storedYear  = parts[2].trim();

            if (storedMonth.equals(normalizedMonth) && storedYear.equals(normalizedYear)) {
                System.out.printf("  %s%n", t);
                if (t instanceof Income)  income  += t.getAmount();
                else                      expense += t.getAmount();
                found = true;
            }
        }
    }

    if (!found) {
        System.out.println("  No transactions found for this period.");
    } else {
        printDivider("-", 80);
        System.out.printf("  Monthly Income   : Rs. %9.2f%n", income);
        System.out.printf("  Monthly Expenses : Rs. %9.2f%n", expense);
        System.out.printf("  Monthly Savings  : Rs. %9.2f%n", income - expense);
    }
    printDivider("=", 80);
}

    // ── Helper ────────────────────────────────────────────────────────
    private void printDivider(String ch, int len) {
        System.out.println(ch.repeat(len));
    }
}