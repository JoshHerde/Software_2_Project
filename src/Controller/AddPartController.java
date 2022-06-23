package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * The AddPart FXML Controller class
 *
 * @author Joshua Herde
 */


public class AddPartController implements Initializable {

    @FXML
    private RadioButton inHouseRadioButton;
    @FXML
    private ToggleGroup tgPartLocation;
    @FXML
    private RadioButton outsourcedRadioButton;
    @FXML
    private Label partMachineIdNameChange;
    @FXML
    private TextField partId;
    @FXML
    private TextField partName;
    @FXML
    private TextField partInventory;
    @FXML
    private TextField partPrice;
    @FXML
    private TextField partMax;
    @FXML
    private TextField partMachineIdName;
    @FXML
    private TextField partMin;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void inHouseRadioButtonClicked(ActionEvent actionEvent) {
        partMachineIdNameChange.setText("Machine ID");
    }

    public void outsourcedRadioButtonClicked(ActionEvent actionEvent) {
        partMachineIdNameChange.setText("Company Name");
    }

    public void saveButtonClicked(ActionEvent actionEvent) {
    }

    public void cancelButtonClicked(ActionEvent actionEvent) {
    }
}