package bd.uber.redwan;

import bd.uber.BinFilePath;
import bd.uber.Util;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class InventorySceneController implements Initializable {
    @FXML
    private TableView<VehicleComponent> inventoryTable;
    @FXML
    private ComboBox<ComponentCategory> categoryComboBox;
    @FXML
    private ComboBox<String> componentComboBox;
    @FXML
    private TableColumn<VehicleComponent, ComponentCategory> categoryTable;
    @FXML
    private TableColumn<VehicleComponent, String> componentNameTable;
    @FXML
    public TableColumn<VehicleComponent, Integer> quantityColumn;
    @FXML
    public ComboBox<Integer> quantityComboBox;

    private final Inventory inventoryTeamMember = new Inventory();

    private final Map<ComponentCategory, List<String>> componentMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Populate categoryComboBox with categories
        categoryComboBox.getItems().addAll(ComponentCategory.values());
        quantityComboBox.getItems().addAll(1, 2, 3, 4, 5);

        categoryComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            componentComboBox.getItems().clear();
            componentComboBox.getItems().addAll(newValue.getComponentList());
        });

        // Set up columns in TableView
        categoryTable.setCellValueFactory(new PropertyValueFactory<>("category"));
        componentNameTable.setCellValueFactory(new PropertyValueFactory<>("componentName"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        loadFromBinFile();
    }

    @FXML
    private void placeOrderButton(ActionEvent event) {
        ComponentCategory selectedCategory = categoryComboBox.getValue();
        String selectedComponent = componentComboBox.getValue();
        int quantity = quantityComboBox.getValue();

        // add validations
        if (Util.getInstance().getDb().addObject(
                new VehicleComponent(selectedComponent, selectedCategory, quantity),
                BinFilePath.PLACED_ORDER
        )) {
            Util.getInstance().showSuccessMessage("Order placed");
        }

    }

    private void loadFromBinFile() {
        Inventory inventory = Util.getInstance().getDb().<Inventory>getObjectList(
                BinFilePath.INVENTORY).get(0);

        for (ComponentCategory category : inventory.getInventory().keySet()) {
            for (Pair<String, Integer> item : inventory.getInventory().get(category)) {
                inventoryTable.getItems()
                        .add(new VehicleComponent(item.getKey(), category, item.getValue()));
            }
        }
    }

    @FXML
    private void viewOrderButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PlacedOrder.fxml"));
        Scene scene = new Scene(loader.load());
        
        Util.getInstance().showScene(scene, event,"Placed Orders");
    }

    @FXML
    private void backButton(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
        Scene scene = new Scene(loader.load());

        stage.setScene(scene);
        stage.show();        
    }
}