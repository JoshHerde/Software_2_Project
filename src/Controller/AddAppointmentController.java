package Controller;

import javafx.event.ActionEvent;
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

    private TextField appointmentTitle;
    private ComboBox contactComboBox;
    private TextField appointmentLoc;
    private TextField appointmentDesc;
    private TextField appointmentType;
    private DatePicker startDatePicker;
    private Spinner startTimeSpinner;
    private DatePicker endDatePicker;
    private Spinner endTimeSpinner;
    private TextField customerId;
    private TextField userId;

    void saveButtonClicked(ActionEvent actionEvent) {

    }

    void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
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
