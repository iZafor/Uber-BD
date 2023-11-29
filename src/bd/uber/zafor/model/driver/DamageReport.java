package bd.uber.zafor.model.driver;

import java.io.Serializable;
import java.time.LocalDate;

public class DamageReport implements Serializable {
    private final int damageReportId;
    private final String description;
    private final LocalDate reportDate;
    private final int locationId;

    public DamageReport(int damageReportId, String description, LocalDate reportDate, int locationId) {
        this.damageReportId = damageReportId;
        this.description = description;
        this.reportDate = reportDate;
        this.locationId = locationId;
    }

    public int getDamageReportId() {
        return damageReportId;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public int getLocationId() {
        return locationId;
    }
}