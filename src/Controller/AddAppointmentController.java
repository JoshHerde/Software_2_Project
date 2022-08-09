package Controller;

import DAO_DBAccess.AppointmentsDAO;
import DAO_DBAccess.ContactsDAO;
import DAO_DBAccess.CustomersDAO;
import DAO_DBAccess.UsersDAO;
import Model.Appointments;
import Model.Contacts;
import Model.Customers;
import Model.Users;
import Utilities.TimeHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML private TextField apptTitleTextField;
    @FXML private TextField apptLocTextField;
    @FXML private TextField apptDescTextField;
    @FXML private TextField apptTypeTextField;
    @FXML private DatePicker startDatePicker;
    @FXML private ComboBox<LocalTime> startTimeComboBox;
    @FXML private DatePicker endDatePicker;
    @FXML private ComboBox<LocalTime> endTimeComboBox;
    @FXML private ComboBox<Contacts> contactComboBox;
    @FXML private ComboBox<Customers> customerIDComboBox;
    @FXML private ComboBox<Users> userIDComboBox;

    public static Customers selectedCustomer;
    public static Appointments selectedAppointment;


    private ObservableList<Contacts> contactList = FXCollections.observableArrayList();
    private ObservableList<Customers> customersList = FXCollections.observableArrayList();
    private ObservableList<Users> usersList = FXCollections.observableArrayList();


    @FXML void saveButtonClicked(ActionEvent actionEvent) {

        try {
            String title = apptTitleTextField.getText();
            String description = apptDescTextField.getText();
            String location = apptLocTextField.getText();
            String type = apptTypeTextField.getText();

            LocalDateTime startTime = getStartInfo();
            LocalDateTime endTime = getEndInfo();

            if (apptTitleTextField.getText().isBlank() && apptDescTextField.getText().isBlank() && apptLocTextField.getText().isBlank() && apptTypeTextField.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Empty Text Field");
                alert.setHeaderText("Please make sure all text fields are filled out");
                alert.showAndWait();
                return;
            }

            int contactID = contactComboBox.getSelectionModel().getSelectedItem().getContactID();
            int customerID = customerIDComboBox.getSelectionModel().getSelectedItem().getCustomerID();
            int userID = userIDComboBox.getSelectionModel().getSelectedItem().getUserID();

            LocalTime openHours = LocalTime.of(8, 0);
            LocalTime closedHours = LocalTime.of(22, 0);

            Appointments newAppointment = new Appointments(title, description, location, type, startTime, endTime, customerID, userID, contactID);


            for (Appointments appointments : AppointmentsDAO.getAllAppointments()) {
                if ((startTime.isEqual(appointments.getStartTime()) || startTime.isAfter(appointments.getStartTime()) && startTime.isBefore(appointments.getEndTime()))) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Scheduling Time Error");
                    alert.setHeaderText("The time you have chosen for your appointment overlaps with others. Please choose a new appointment time.");
                    alert.showAndWait();
                    return;
                }

                if (startTime.toLocalTime().isBefore(openHours) || endTime.toLocalTime().isAfter(closedHours)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Scheduling Time Error");
                    alert.setHeaderText("The time you have chosen for your appointment isn't within business hours of 08:00 EST and 10:00 EST.");
                    alert.showAndWait();
                    return;
                }
            }
            AppointmentsDAO.addAppointment(newAppointment);

            Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            System.out.println("root = " + root + " stage = " + stage + " scene = " + scene);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LocalDateTime getStartInfo() {
        LocalDate startDate = startDatePicker.getValue();
        LocalTime startTime = startTimeComboBox.getValue();
        return LocalDateTime.of(startDate, startTime);
    }

    private LocalDateTime getEndInfo() {
        LocalDate endDate = endDatePicker.getValue();
        LocalTime endTime = endTimeComboBox.getValue();
        return  LocalDateTime.of(endDate, endTime);
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

        startTimeComboBox.setItems(TimeHelper.getStartTimes());
        endTimeComboBox.setItems(TimeHelper.getEndTimes());

        try {
            contactList = ContactsDAO.getAllContacts();
            contactComboBox.setItems(contactList);

            customersList = CustomersDAO.getAllCustomers();
            customerIDComboBox.setItems(customersList);

            usersList = UsersDAO.getAllUsers();
            userIDComboBox.setItems(usersList);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
