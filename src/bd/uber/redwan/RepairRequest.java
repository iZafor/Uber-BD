package bd.uber.redwan;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Redwan
 */
public class RepairRequest implements Serializable {

    private String requestId;
    private int driverId;
    private List<DamageReport> damageReports;

    public RepairRequest(String requestId, int driverId, List<DamageReport> damageReports) {
        this.requestId = requestId;
        this.driverId = driverId;
        this.damageReports = damageReports;
    }

    public static List<RepairRequest> generateSampleRepairRequests() {
        List<RepairRequest> repairRequests = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            String requestId = "REQ_" + String.format("%03d", i + 1);
            int driverId = 10000 + random.nextInt(90000); // Generates random driver IDs between 10000 and 99999

            int numberOfReports = 2 + random.nextInt(9); // Generates a random number between 2 and 10 reports for each request
            List<DamageReport> sampleDamageReports = DamageReport.generateSampleDamageReports();
            numberOfReports = Math.min(9, sampleDamageReports.size()); // Update the value if it exceeds the size
            List<DamageReport> damageReports = sampleDamageReports.subList(0, numberOfReports);

            RepairRequest request = new RepairRequest(requestId, driverId, damageReports);
            repairRequests.add(request);
        }

        return repairRequests;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public List<DamageReport> getDamageReports() {
        return damageReports;
    }

    public void setDamageReports(List<DamageReport> damageReports) {
        this.damageReports = damageReports;
    }

    public String getFindingsForReport(int reportIndex) {
        List<DamageReport> reports = this.getDamageReports();
        if (reports != null && reportIndex >= 0 && reportIndex < reports.size()) {
            DamageReport report = reports.get(reportIndex);
            return report.getFindings();
        }
        return "No findings available for this report.";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Request ID: ").append(requestId).append(", Driver ID: ").append(driverId).append("\n");
        sb.append("Damage Reports:\n");
        for (DamageReport report : damageReports) {
            sb.append(report.toString()).append("\n");
        }
        return sb.toString();
    }

}
