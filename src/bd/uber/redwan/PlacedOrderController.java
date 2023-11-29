/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package bd.uber.redwan;

import bd.uber.BinFilePath;
import bd.uber.Util;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

/**
 * FXML Controller class
 *
 * @author Redwan
 */
public class PlacedOrderController implements Initializable {

    @FXML
    private TableView<VehicleComponent> inventoryTable;
    @FXML
    private TableColumn<VehicleComponent, ComponentCategory> categoryTable;
    @FXML
    private TableColumn<VehicleComponent, String> componentNameTable;
    @FXML
    public TableColumn<VehicleComponent, Integer> quantityColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        categoryTable.setCellValueFactory(new PropertyValueFactory<>("category"));
        componentNameTable.setCellValueFactory(new PropertyValueFactory<>("componentName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadFromBinFile();
    } 
    
    private void loadFromBinFile() {
        List <VehicleComponent>  orderedComponents = Util.getInstance().getDb().<VehicleComponent>getObjectList(
                BinFilePath.PLACED_ORDER);
        for (VehicleComponent component:orderedComponents) {
            inventoryTable.getItems().add(component);
        }

    }
    
}
