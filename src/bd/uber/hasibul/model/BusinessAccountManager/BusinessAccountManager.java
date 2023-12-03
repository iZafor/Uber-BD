package bd.uber.hasibul.model.BusinessAccountManager;

import bd.uber.BinFilePath;
import bd.uber.Employee;
import bd.uber.Util;
import bd.uber.zafor.model.driver.Driver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BusinessAccountManager extends Employee implements Serializable {
    private final List<Employee> employeeList = new ArrayList<>();
    private final List<Driver> driverList = Util.getInstance().getDb().getObjectList(BinFilePath.DRIVER);
    private final BusinessAnalytics businessAnalytics = new BusinessAnalytics();

    public BusinessAccountManager(int id) {
        super(id);
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public BusinessAnalytics getBusinessAnalytics() {
        return businessAnalytics;
    }
}