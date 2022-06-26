package Controller;

import DAO_DBAccess.CountriesDAO;
import Model.Countries;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AddCustomerController {

    @FXML private TextField nameTextField;
    @FXML private  TextField addressTextField;
    @FXML private  TextField postalCodeTextField;
    @FXML private  TextField phoneTextField;
    @FXML private  ComboBox countryComboBox;
    @FXML private  ComboBox divisionComboBox;

    private ObservableList<Countries> countryList = FXCollections.observableArrayList();

    @FXML void saveButtonClicked(ActionEvent actionEvent) {
    }

    @FXML void cancelButtonClicked(ActionEvent actionEvent) {
    }

    public void countryComboBoxClicked(ActionEvent actionEvent) {
        ObservableList<Countries> countryList = CountriesDAO.getAllCountries();
        for(Countries c : countryList) {
            System.out.println();
        }
    }
}
