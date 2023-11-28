package bd.uber.zafor.model.driver;

import bd.uber.Location;

import java.io.Serializable;
import java.time.LocalDate;

public class DamageReport implements Serializable {
    private final int damageReportId;
    private final String description;
    private final LocalDate reportDate;
    private final Location location;

    public DamageReport(int damageReportId, String description, LocalDate reportDate, Location location) {
        this.damageReportId = damageReportId;
        this.description = description;
        this.reportDate = reportDate;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }
}