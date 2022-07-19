package Controller;

import Model.Contacts;
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
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML private TextField apptTitleTextField;
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private TextField apptLocTextField;
    @FXML private TextField apptDescTextField;
    @FXML private TextField apptTypeTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private Spinner<LocalTime> startTimeSpinner;
    @FXML private DatePicker endDatePicker;
    @FXML private Spinner<LocalTime> endTimeSpinner;
    @FXML private TextField customerIdTextField;
    @FXML private TextField userIdTextField;

    @FXML void saveButtonClicked(ActionEvent actionEvent) {

    }

    @FXML void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
