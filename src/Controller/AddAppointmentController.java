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


    private ObservableList<Contacts> contactList = FXCollections.observableArrayList();
    private ObservableList<Customers> customersList = FXCollections.observableArrayList();
    private ObservableList<Users> usersList = FXCollections.observableArrayList();


    @FXML void saveButtonClicked(ActionEvent actionEvent) {
        try {
            String title = apptTitleTextField.getText();
            String description = apptDescTextField.getText();
            String location = apptLocTextField.getText();
            String type = apptTypeTextField.getText();

            //Start date and time
            LocalDate startDate = startDatePicker.getValue();
            LocalTime startTime = startTimeComboBox.getValue();
            LocalDateTime startDateTime = LocalDateTime.of(startDate, startTime);

            //End date and time
            LocalDate endDate = endDatePicker.getValue();
            LocalTime endTime = endTimeComboBox.getValue();
            LocalDateTime endDateTime = LocalDateTime.of(endDate, endTime);

            int contactID = contactComboBox.getSelectionModel().getSelectedItem().getContactID();
            int customerID = customerIDComboBox.getSelectionModel().getSelectedItem().getCustomerID();
            int userID = userIDComboBox.getSelectionModel().getSelectedItem().getUserID();

            Appointments newAppointment = new Appointments(title, description, location, type, startDateTime, endDateTime, contactID, customerID, userID);
            AppointmentsDAO.addAppointment(newAppointment);

            Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
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
