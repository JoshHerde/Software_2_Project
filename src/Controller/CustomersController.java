package Controller;

import DAO_DBAccess.CustomersDAO;
import Model.Customers;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomersController implements Initializable {


    @FXML private TableView<Customers> customersTable;
    @FXML private TableColumn<Customers, String> customerNameCol;
    @FXML private TableColumn<Customers, String> customerAddressCol;
    @FXML private TableColumn<Customers, String> customerPostalCol;
    @FXML private TableColumn<Customers, String> customerPhoneCol;
    @FXML private TableColumn<Customers, Integer> customerDivisionCol;
    @FXML private TextField searchTextField;

    private static Customers customerToModify;

    public static Customers getCustomerToModify() {
        return customerToModify;
    }


    @FXML  void newCustomerClicked(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML  void editCustomerClicked(ActionEvent actionEvent) throws IOException {
        customerToModify = customersTable.getSelectionModel().getSelectedItem();

        if (customerToModify == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select a customer to Edit.");
            alert.showAndWait();
            return;
        }
        Parent root = FXMLLoader.load(getClass().getResource("/View/EditCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customersTable.setItems(CustomersDAO.getAllCustomers());
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        customerPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
    }
}
