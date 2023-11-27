/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

    // Method to generate a PDF report
    public void generatePdf() {
        // Logic to generate a PDF report from the maintenance details
        // This method can handle the creation of a PDF report based on the report details
    }

    // Method to archive maintenance record
    public void archiveMaintenanceRecord(String archiveLocation) {
        // Logic to archive the maintenance record
        // This method can store or archive the maintenance record at the specified location
    }

    // Other methods as needed
}
