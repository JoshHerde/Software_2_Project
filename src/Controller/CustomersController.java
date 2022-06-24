package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML  void editCustomerClicked(ActionEvent actionEvent) {
    }

    @FXML void deleteCustomerClicked(ActionEvent actionEvent) {
    }

    @FXML void backButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
