package bd.uber.zafor;

import bd.uber.Location;

import java.io.Serializable;
import java.time.LocalDate;

public class DamageReport implements Serializable {
    private final String description;
    private final LocalDate reportDate;
    private final Location location;

    public DamageReport(String description, LocalDate reportDate, Location location) {
        this.description = description;
        this.reportDate = reportDate;
        this.location = location;
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