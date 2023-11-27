package bd.uber.zafor.model.driver;

public enum MechanicalIssue {
    ENGINE_ISSUE("Engine issue"),
    TRANSMISSION_PROBLEM("Transmission problem"),
    BRAKE_SYSTEM_TROUBLE("Brake system trouble"),
    SUSPENSION_PROBLEM("Suspension problem"),
    STEERING_PROBLEM("Steering problem"),
    ELECTRICAL_SYSTEM_MALFUNCTION("Electrical system malfunction"),
    COLLING_SYSTEM_FAILURE("Colling system failure"),
    EXHAUST_SYSTEM_ISSUE("Exhaust system issue"),
    FUEL_SYSTEM_PROBLEM("Fuel system problem"),
    OTHER("Other"),
    NONE("None");

    private final String issue;

    MechanicalIssue(String issue) {
        this.issue = issue;
    }

    @Override
    public String toString() {
        return issue;
    }
}