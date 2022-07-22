package Controller;

import DAO_DBAccess.CountriesDAO;
import DAO_DBAccess.CustomersDAO;
import DAO_DBAccess.DivisionsDAO;
import Model.Countries;
import Model.Customers;
import Model.Divisions;
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

public class AddCustomerController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private  TextField addressTextField;
    @FXML private  TextField postalCodeTextField;
    @FXML private  TextField phoneTextField;
    @FXML private  ComboBox<Countries> countryComboBox;
    @FXML private  ComboBox<Divisions> divisionComboBox;


    private ObservableList<Countries> countryList = FXCollections.observableArrayList();

    @FXML void saveButtonClicked(ActionEvent actionEvent) {
        try {
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phone = phoneTextField.getText();
            int divisionID = divisionComboBox.getSelectionModel().getSelectedItem().getDivisionID();

            Customers newCustomer = new Customers(name, address, postalCode, phone, divisionID);
            CustomersDAO.addCustomer(newCustomer);

            Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void countryComboBoxClicked(ActionEvent actionEvent) {
        try {
            Countries selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
            ObservableList<Divisions> dbDivisions = DivisionsDAO.getAllDivisions();
            ObservableList<Divisions> divisionByCountry = FXCollections.observableArrayList();

            for (Divisions divisions : dbDivisions) {
                if (divisions.getCountryID() == selectedCountry.getCountryID()) {
                    divisionByCountry.add(divisions);
                }
            }
            divisionComboBox.setItems(divisionByCountry);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void divisionComboBoxClicked(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countryList = CountriesDAO.getAllCountries();
        countryComboBox.setItems(countryList);
    }
}
