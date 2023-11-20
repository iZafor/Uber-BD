package bd.uber.zafor.model;

import java.io.Serializable;

public enum PaymentMethod implements Serializable {
    CASH("Cash"),
    DIGITAL("Digital");

    private final String paymentMethod;

    PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return paymentMethod;
    }
}