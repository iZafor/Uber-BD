
package bd.uber.redwan;

/**
 *
 * @author Redwan
 */
public class Report {
    private String reportId;
    private String findings;

    // Constructor
    public Report(String reportId, String findings) {
        this.reportId = reportId;
        this.findings = findings;
    }

    // Getters and setters
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }
    
    // Other methods as needed
}
