package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.time.LocalDate;

public class InsurancePolicy implements Serializable {
    private final int insurancePolicyId;
    private String policyNumber;
    private String provider;
    private float coverageAmount;
    private LocalDate expirationDate;

    public InsurancePolicy(int insurancePolicyId) {
        this.insurancePolicyId = insurancePolicyId;
    }

    public int getInsurancePolicyId() {
        return insurancePolicyId;
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