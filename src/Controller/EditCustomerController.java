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
    private Users currentUser = LoginController.getCurrentUser();
    private Customers selectedCustomer;




    @FXML void saveButtonClicked(ActionEvent actionEvent) {
        try {
            String name = nameTextField.getText();
            String address = addressTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String phone = phoneTextField.getText();
            int divisionID = divisionComboBox.getSelectionModel().getSelectedItem().getDivisionID();

            Customers newCustomer = new Customers(name, address, postalCode, phone, divisionID);
            newCustomer.setCustomerID(selectedCustomer.getCustomerID());

            Parent root = FXMLLoader.load(getClass().getResource("/view/Customers.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void countryComboBoxClicked(ActionEvent actionEvent) {
        try {
            Countries selectedCountry = countryComboBox.getSelectionModel().getSelectedItem();
            ObservableList<Divisions> databaseDivisions = DivisionsDAO.getAllDivisions();
            ObservableList<Divisions> countryDivisions = FXCollections.observableArrayList();

            for (Divisions divisions : databaseDivisions) {
                if (divisions.getCountryID() == selectedCountry.getCountryID()) {
                    countryDivisions.add(divisions);
                }
            }
            divisionComboBox.setItems(countryDivisions);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectedCustomer = CustomersController.getSelectedCustomer();

        customerIDTextField.setText(String.valueOf(selectedCustomer.getCustomerID()));
        nameTextField.setText(String.valueOf(selectedCustomer.getName()));
        addressTextField.setText(selectedCustomer.getAddress());
        postalCodeTextField.setText(selectedCustomer.getPostalCode());
        phoneTextField.setText(selectedCustomer.getPhone());

        try {
            Divisions selectedDivision = DivisionsDAO.getDivisionName(selectedCustomer.getDivisionID());
            Countries countries = CountriesDAO.getCountryByID(selectedDivision.getCountryID());

            countryList = CountriesDAO.getAllCountries();
            countryComboBox.setItems(countryList);

            ObservableList<Divisions> databaseDivisions = DivisionsDAO.getAllDivisions();
            ObservableList<Divisions> countryDivisions = FXCollections.observableArrayList();

            for (Divisions divisions : databaseDivisions) {

            }

            divisionComboBox.setItems(countryDivisions);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
