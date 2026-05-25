package finance;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages account balance and all transactions.
 *
 * OOP Concepts:
 *  - ENCAPSULATION: balance and transactions are private;
 *                   exposed only through validated public methods
 */
public class Account {

    // ── Private Fields ────────────────────────────────────────────────
    private final String ownerName;
    private double balance;
    private final List<Transaction> transactions;

    // ── Constructor ───────────────────────────────────────────────────
    public Account(String ownerName, double initialBalance) {
        if (ownerName == null || ownerName.trim().isEmpty())
            throw new IllegalArgumentException("Owner name cannot be empty.");
        if (initialBalance < 0)
            throw new IllegalArgumentException("Initial balance cannot be negative.");

        this.ownerName    = ownerName.trim();
        this.balance      = initialBalance;
        this.transactions = new ArrayList<>();
    }

    // ── Public Methods ────────────────────────────────────────────────

    /**
     * Adds a transaction and updates balance accordingly.
     * Polymorphism: getSignedAmount() resolves at runtime to
     * Income(+) or Expense(-) implementation.
     */
    public void addTransaction(Transaction t) {
        if (t == null)
            throw new IllegalArgumentException("Transaction cannot be null.");

        // Check for sufficient funds before an expense
        if (t instanceof Expense && balance + t.getSignedAmount() < 0) {
            System.out.println("  [WARNING] Insufficient balance! "
                + "Current balance: Rs." + String.format("%.2f", balance)
                + "  |  Expense: Rs." + String.format("%.2f", t.getAmount()));
            System.out.println("  Transaction was NOT added.\n");
            return;
        }

        transactions.add(t);
        balance += t.getSignedAmount();   // Polymorphism in action
        System.out.println("  Transaction added successfully. "
            + "New balance: Rs." + String.format("%.2f", balance));
    }

    // ── Getters ───────────────────────────────────────────────────────
    public double getBalance()              { return balance; }
    public String getOwnerName()            { return ownerName; }

    /** Returns an unmodifiable view so callers cannot alter the list */
    public List<Transaction> getTransactions() {
        return java.util.Collections.unmodifiableList(transactions);
    }
}