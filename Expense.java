package finance;

/**
 * Represents an expense transaction.
 *
 * OOP Concepts:
 *  - INHERITANCE  : Extends Transaction
 *  - POLYMORPHISM : Overrides getType() and getSignedAmount()
 */
public class Expense extends Transaction {

    public Expense(double amount, String category,
                   String date) {
        super(amount, category, date);
    }

    @Override
    public String getType() {
        return "EXPENSE";
    }

    /** Expense deducts from balance → negative value */
    @Override
    public double getSignedAmount() {
        return -getAmount();
    }
}