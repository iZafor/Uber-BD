package bd.uber.zafor;

import java.io.Serializable;
import java.time.LocalDate;

public class InsurancePolicy implements Serializable {
    private String policyNumber;
    private String provider;
    private float coverageAmount;
    private LocalDate expirationDate;

    public InsurancePolicy(String policyNumber, String provider, float coverageAmount, LocalDate expirationDate) {
        this.policyNumber = policyNumber;
        this.provider = provider;
        this.coverageAmount = coverageAmount;
        this.expirationDate = expirationDate;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public float getCoverageAmount() {
        return coverageAmount;
    }

    public void setCoverageAmount(float coverageAmount) {
        this.coverageAmount = coverageAmount;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}