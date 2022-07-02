package Controller;

import Model.Countries;
import Model.Divisions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class EditCustomerController {

    @FXML private TextField nameTextField;
    @FXML private  TextField addressTextField;
    @FXML private  TextField postalCodeTextField;
    @FXML private  TextField phoneTextField;
    @FXML private  ComboBox<Countries> countryComboBox;
    @FXML private  ComboBox<Divisions> divisionComboBox;

    @FXML void saveButtonClicked(ActionEvent actionEvent) {
    }

    @FXML void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
