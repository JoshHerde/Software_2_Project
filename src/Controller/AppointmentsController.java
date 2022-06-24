package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {

    @FXML private TableView appointmentsTable;
    @FXML private TableColumn idCol;
    @FXML private TableColumn titleCol;
    @FXML private TableColumn descCol;
    @FXML private TableColumn locCol;
    @FXML private TableColumn contactCol;
    @FXML private TableColumn typeCol;
    @FXML private TableColumn startCol;
    @FXML private TableColumn endCol;
    @FXML private TableColumn customerIdCol;
    @FXML private TableColumn userIdCol;
    @FXML private  RadioButton monthRadioButton;
    @FXML private  RadioButton weekRadioButton;
    @FXML private  TextField searchTextField;

    @FXML void monthRbClicked(ActionEvent actionEvent) {
    }

    @FXML void weekRbClicked(ActionEvent actionEvent) {
    }

    @FXML void newAppointmentClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void editAppointmentClicked(ActionEvent actionEvent) {
    }

    @FXML void deleteAppointmentClicked(ActionEvent actionEvent) {
    }

    @FXML void exitButtonClicked(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML void customerButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Customers.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
