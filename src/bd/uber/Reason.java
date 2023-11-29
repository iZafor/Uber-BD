package bd.uber;

import java.io.Serializable;

public class Reason implements Serializable {
    private final int reasonId;
    private String reasonDescription;

    public Reason(int reasonId) {
        this.reasonId = reasonId;
    }

    public int getReasonId() {
        return reasonId;
    }

    public String getReasonDescription() {
        return reasonDescription;
    }

    public void setReasonDescription(String reasonDescription) {
        this.reasonDescription = reasonDescription;
    }
}