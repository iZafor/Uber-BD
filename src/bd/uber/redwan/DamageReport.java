package bd.uber.redwan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Redwan
 */
public class DamageReport implements Serializable {

    private String damageId;
    private String description;
    private int estimatedCost;

    private String findings;

    public String getFindings() {
        return findings;
    }

    public void setFindings(String findings) {
        this.findings = findings;
    }

    public DamageReport(String damageId, String description, int estimatedCost) {
        this.damageId = damageId;
        this.description = description;
        this.estimatedCost = estimatedCost;
    }

    public DamageReport(int estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public String getDamageId() {
        return damageId;
    }

    public void setDamageId(String damageId) {
        this.damageId = damageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimatedCost() {
        return estimatedCost;
    }

    public void setEstimatedCost(int estimatedCost) {
        this.estimatedCost = estimatedCost;
    }

    public static List<DamageReport> generateSampleDamageReports() {
        List<DamageReport> damageReports = new ArrayList<>();
        Random random = new Random();

        int numberOfReports = 5 + random.nextInt(6);

        for (int i = 0; i < numberOfReports; i++) {
            String damageId = "DR" + String.format("%03d", i + 1);
            String description = "Damage " + (i + 1);
            int estimatedCost = 100 + random.nextInt(201);

            DamageReport report = new DamageReport(damageId, description, estimatedCost);
            damageReports.add(report);
        }

        String[] realisticFindings = {
            "Minor scratches on the bumper.",
            "Dent on the driver-side door.",
            "Cracked windshield.",
            "Scratches on the rear fender.",
            "Scuff marks on the front bumper."
        };

        for (DamageReport report : damageReports) {
            int index = random.nextInt(realisticFindings.length);

            report.setFindings(realisticFindings[index]);
        }
        return damageReports;
    }

    @Override
    public String toString() {
        return "Damage ID: " + damageId + ", Description: " + description + ", Estimated Cost: $" + estimatedCost;
    }
}
