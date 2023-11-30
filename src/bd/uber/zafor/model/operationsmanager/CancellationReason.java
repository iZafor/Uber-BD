package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public class CancellationReason implements Serializable {
    private ReasonCode reasonCode;
    private String description;

    public ReasonCode getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(ReasonCode reasonCode) {
        this.reasonCode = reasonCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}