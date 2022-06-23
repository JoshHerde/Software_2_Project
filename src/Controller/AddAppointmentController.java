package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML private TextField appointmentTitle;
    @FXML private ComboBox contactComboBox;
    @FXML private TextField appointmentLoc;
    @FXML private TextField appointmentDesc;
    @FXML private TextField appointmentType;
    @FXML private DatePicker startDatePicker;
    @FXML private Spinner startTimeSpinner;
    @FXML private DatePicker endDatePicker;
    @FXML private Spinner endTimeSpinner;
    @FXML private TextField customerId;
    @FXML private TextField userId;

    @FXML void saveButtonClicked(ActionEvent actionEvent) {

    }

    @FXML void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainScreen.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
