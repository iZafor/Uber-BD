
package bd.uber.redwan;

/**
 *
 * @author Redwan
 */
public class Report {
    private String reportId;
    private String findings;

    public Report(String reportId, String findings) {
        this.reportId = reportId;
        this.findings = findings;
    }

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
    
}
