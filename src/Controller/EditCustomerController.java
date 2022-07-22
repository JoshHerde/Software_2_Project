package Controller;

import DAO_DBAccess.CountriesDAO;
import DAO_DBAccess.DivisionsDAO;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
import Model.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditCustomerController implements Initializable {

    @FXML private TextField customerIDTextField;
    @FXML private TextField nameTextField;
    @FXML private  TextField addressTextField;
    @FXML private  TextField postalCodeTextField;
    @FXML private  TextField phoneTextField;
    @FXML private  ComboBox<Countries> countryComboBox;
    @FXML private  ComboBox<Divisions> divisionComboBox;

    private ObservableList<Countries> countryList = FXCollections.observableArrayList();



    @FXML void saveButtonClicked(ActionEvent actionEvent) {
    }

    @FXML void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void countryComboBoxClicked(ActionEvent actionEvent) {

    }

    @FXML void divisionComboBoxClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Customers selectedCustomer = CustomersController.getSelectedCustomer();

        customerIDTextField.setText(String.valueOf(selectedCustomer.getCustomerID()));
        nameTextField.setText(String.valueOf(selectedCustomer.getName()));
        addressTextField.setText(selectedCustomer.getAddress());
        postalCodeTextField.setText(selectedCustomer.getPostalCode());
        phoneTextField.setText(selectedCustomer.getPhone());

        countryList = CountriesDAO.getAllCountries();
        countryComboBox.setItems(countryList);

    }


}
