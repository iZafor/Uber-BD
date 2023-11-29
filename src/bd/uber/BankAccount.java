package bd.uber;

import java.io.Serializable;

public class BankAccount implements Serializable {
    private final int bankAccountId;
    private final String accountNumber;
    private final String bankName;

    public BankAccount(int bankAccountId, String accountNumber, String bankName) {
        this.bankAccountId = bankAccountId;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }

    public void transferFund(float fund) {

    }

    public int getBankAccountId() {
        return bankAccountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankName() {
        return bankName;
    }
}