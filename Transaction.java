package finance;

/**
 * Abstract base class representing a financial transaction.
 *
 * OOP Concepts:
 *  - ABSTRACTION  : Abstract class with abstract methods
 *  - ENCAPSULATION: Private fields with public getters only
 */
public abstract class Transaction {

    // ── Private Fields (Encapsulation) ────────────────────────────────
    private final double amount;
    private final String category;
    private final String date;          // Format: DD-MM-YYYY

    // ── Constructor ───────────────────────────────────────────────────
    public Transaction(double amount, String category, String date) {
        if (amount <= 0)
            throw new IllegalArgumentException(
                "Amount must be positive. Got: " + amount);
        if (category == null || category.trim().isEmpty())
            throw new IllegalArgumentException("Category cannot be empty.");
        if (date == null || date.trim().isEmpty())
            throw new IllegalArgumentException("Date cannot be empty.");

        this.amount      = amount;
        this.category    = category.trim();
        this.date        = date.trim();
    }

    // ── Getters (controlled access) ───────────────────────────────────
    public double getAmount()      { return amount; }
    public String getCategory()    { return category; }
    public String getDate()        { return date; }

    // ── Abstract Methods (Polymorphism) ───────────────────────────────
    /** Returns "INCOME" or "EXPENSE" */
    public abstract String getType();

    /** Returns signed effect on balance: +amount or -amount */
    public abstract double getSignedAmount();

    @Override
    public String toString() {
        return String.format(
            "  %-8s | Date: %-12s | Category: %-14s | Rs.%9.2f ",
            getType(), date, category, amount);
    }
}