package finance;

/**
 * Represents an income transaction.
 *
 * OOP Concepts:
 *  - INHERITANCE  : Extends Transaction
 *  - POLYMORPHISM : Overrides getType() and getSignedAmount()
 */
public class Income extends Transaction {

    public Income(double amount, String category,
                  String date) {
        super(amount, category, date);
    }

    @Override
    public String getType() {
        return "INCOME";
    }

    /** Income adds to balance → positive value */
    @Override
    public double getSignedAmount() {
        return +getAmount();
    }
}