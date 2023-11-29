/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nisi.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jannatul Ferdous
 */
public class EmployeeFile {
//    private static final String EMPLOYEE_FILE_PATH = "Employees.bin";
//    private static final String SHIFT_FILE_PATH = "Shifts.bin";
//    private static final File employeeFile = new File(EMPLOYEE_FILE_PATH);
//    private static final File shiftFile = new File(SHIFT_FILE_PATH);
//
//    public static boolean addEmployee(EmployeeInformation employee) {
//        boolean append = employeeFile.exists();
//        try {
//            FileOutputStream fos = new FileOutputStream(employeeFile, append);
//            ObjectOutputStream oos = (append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos));
//            oos.writeObject(employee);
//            oos.close();
//            fos.close();
//            return true;
//        } catch (IOException ignored) {
//
//        }
//        return false;
//    }
//
//    public static boolean addEmployees(List<EmployeeInformation> list) {
//        boolean append = employeeFile.exists();
//        try {
//            FileOutputStream fos = new FileOutputStream(employeeFile, append);
//            ObjectOutputStream oos = (append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos));
//            for(EmployeeInformation employee: list) {
//                oos.writeObject(employee);
//            }
//            oos.close();
//            fos.close();
//            return true;
//        } catch (IOException ignored) {
//
//        }
//        return false;
//    }
//
//    public static boolean addShits(List<Shift> list) {
//        boolean append = shiftFile.exists();
//        try {
//            FileOutputStream fos = new FileOutputStream(shiftFile, append);
//            ObjectOutputStream oos = (append ? new AppendableObjectOutputStream(fos) : new ObjectOutputStream(fos));
//            for(Shift shift: list) {
//                oos.writeObject(shift);
//            }
//            oos.close();
//            fos.close();
//            return true;
//        } catch (IOException ignored) {
//
//        }
//        return false;
//    }
//
//    public static List<EmployeeInformation> getEmployees() {
//        List<EmployeeInformation> employeeList = new ArrayList<>();
//        FileInputStream fis = null;
//        ObjectInputStream ois = null;
//        try {
//            fis = new FileInputStream(EMPLOYEE_FILE_PATH);
//            ois = new ObjectInputStream(fis);
//            while (true) {
//                employeeList.add((EmployeeInformation) ois.readObject());
//            }
//        } catch (IOException | ClassNotFoundException ignored) {
//
//        } finally {
//            try {
//                fis.close();
//                ois.close();
//            } catch (Exception ignored) {
//
//            }
//        }
//        return employeeList;
//    }
//
//    public static List<Shift> getShifts() {
//        List<Shift> shifts = new ArrayList<>();
//        FileInputStream fis = null;
//        ObjectInputStream ois = null;
//        try {
//            fis = new FileInputStream(SHIFT_FILE_PATH);
//            ois = new ObjectInputStream(fis);
//            while (true) {
//                shifts.add((Shift) ois.readObject());
//            }
//        } catch (IOException | ClassNotFoundException ignored) {
//
//        } finally {
//            try {
//                fis.close();
//                ois.close();
//            } catch (Exception ignored) {
//
//            }
//        }
//        return shifts;
//    }
}
