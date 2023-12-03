package bd.uber.redwan;


public class MaintenanceInspection implements Inspection {
    private Report report;
    private MaintenanceDetails maintenanceDetails;
    private MaintenanceTask maintenanceTask;
    private Report generatedReport; // Store the generated report

    public MaintenanceInspection(Report report, MaintenanceDetails details, MaintenanceTask task) {
        this.report = report;
        this.maintenanceDetails = details;
        this.maintenanceTask = task;
    }

    // Getters and setters for MaintenanceDetails and MaintenanceTask
    public MaintenanceDetails getMaintenanceDetails() {
        return maintenanceDetails;
    }

    public void setMaintenanceDetails(MaintenanceDetails maintenanceDetails) {
        this.maintenanceDetails = maintenanceDetails;
    }

    public MaintenanceTask getMaintenanceTask() {
        return maintenanceTask;
    }

    public void setMaintenanceTask(MaintenanceTask maintenanceTask) {
        this.maintenanceTask = maintenanceTask;
    }

    
    public void generateReport() {
        String reportId = report.getReportId();
        String findings = report.getFindings();

        String generatedReportString = "Report ID: " + reportId + "\nFindings: " + findings + "\nAdditional details: ..."; // Modify this to suit your logic

        this.generatedReport = new Report(reportId, generatedReportString);
    }

    public String getGeneratedReportId() {
        if (generatedReport != null) {
            return generatedReport.getReportId();
        }
        return null; 
    }

    // Method to retrieve the generated findings/details
    public String getGeneratedFindings() {
        if (generatedReport != null) {
            return generatedReport.getFindings();
        }
        return null; 
    }

   
    public void updateStatus() {
    }

    
    public void submitReport() {
    }

}
