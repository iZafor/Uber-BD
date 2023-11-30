package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public enum ClaimStatus implements Serializable {
    OPEN,
    ASSIGNED,
    RESOLVED
}