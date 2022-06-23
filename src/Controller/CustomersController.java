package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomersController {


    @FXML private TableView customersTable;
    @FXML private TableColumn customerNameCol;
    @FXML private TableColumn customerAddressCol;
    @FXML private TableColumn customerPostalCol;
    @FXML private TableColumn customerPhoneCol;
    @FXML private TableColumn customerDivisionCol;
    @FXML private TextField searchTextField;

    @FXML  void newCustomerClicked(ActionEvent actionEvent) {
    }

    @FXML  void modifyCustomerClicked(ActionEvent actionEvent) {
    }

    @FXML void deleteCustomerClicked(ActionEvent actionEvent) {
    }

    @FXML void backButtonClicked(ActionEvent actionEvent) {
    }
}
