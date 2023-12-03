/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bd.uber.redwan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


 
/**
 *
 * @author Redwan
 */

public class PreventiveMaintenancePlanning {

    private static final String DIRECTORY_PATH = "src" + File.separator + "bd" + File.separator + "uber" + File.separator + "redwan" + File.separator;

    public void createTextFile(String fileName, String content) {
        String filePath = DIRECTORY_PATH + fileName;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}