package bd.uber.hasibul.model.PaymentProcessor;

import bd.uber.BankAccount;

public class Transaction {
    private final float amount;
    private final BankAccount bankAccount;

    public Transaction(float amount, BankAccount bankAccount) {
        this.amount = amount;
        this.bankAccount = bankAccount;
    }

    public float getAmount() {
        return amount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}