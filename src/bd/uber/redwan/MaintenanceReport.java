package bd.uber.redwan;

import java.time.LocalDate;

/**
 *
 * @author Redwan
 */
public class MaintenanceReport {
    private int vehicleId;
    private String to;
    private String from;
    private LocalDate date;
    private String reportBody;

    // Constructor
    public MaintenanceReport(int vehicleId, String to, String from, LocalDate date, String reportBody) {
        this.vehicleId = vehicleId;
        this.to = to;
        this.from = from;
        this.date = date;
        this.reportBody = reportBody;
    }

    public void generatePdf() {

    }

    public void archiveMaintenanceRecord(String archiveLocation) {
    }

}
