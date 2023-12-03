package bd.uber.hasibul.model.PaymentProcessor;

import bd.uber.Employee;

import java.util.HashMap;
import java.util.Map;

public class PaymentProcessor extends Employee {
    private final Map<String, Double> availableFunds = new HashMap<>();
    private final Map<String, Transaction> tracsactionMap = new HashMap<>();

    public PaymentProcessor(int id) {
        super(id);
    }
}