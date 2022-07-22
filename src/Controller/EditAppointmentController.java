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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditAppointmentController implements Initializable {



    @FXML private TextField apptIdTextField;
    @FXML private  TextField apptTitleTextField;
    @FXML private  TextField apptDescTextField;
    @FXML private  TextField apptLocTextField;
    @FXML private  ComboBox<Contacts> contactComboBox;
    @FXML private  TextField apptTypeTextField;
    @FXML private  DatePicker startDatePicker;
    @FXML private ComboBox<LocalTime> startTimeComboBox;
    @FXML private  DatePicker endDatePicker;
    @FXML private ComboBox<LocalTime> endTimeComboBox;
    @FXML private ComboBox<Customers> customerIDComboBox;
    @FXML private ComboBox<Users> userIDComboBox;

    public Contacts selectedContact;
    public Customers selectedCustomer;
    public Users selectedUser;

    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private ObservableList<Contacts> contactList = FXCollections.observableArrayList();
    private ObservableList<Customers> customersList = FXCollections.observableArrayList();
    private ObservableList<Users> usersList = FXCollections.observableArrayList();

    @FXML void cancelButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void saveButtonClicked(ActionEvent actionEvent) throws IOException {
        String title = apptTitleTextField.getText();
        String description = apptDescTextField.getText();
        String location = apptLocTextField.getText();
        String type = apptTypeTextField.getText();
        LocalDateTime startDateTime = LocalDateTime.of(startDatePicker.getValue(), LocalTime.parse(startTimeComboBox.getValue().toString()));
        LocalDateTime endDateTime = LocalDateTime.of(endDatePicker.getValue(), LocalTime.parse(endTimeComboBox.getValue().toString()));
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Appointments selectedAppointment = AppointmentsController.getSelectedAppointment();

        apptIdTextField.setText(String.valueOf(selectedAppointment.getAppointmentID()));
        apptTitleTextField.setText(selectedAppointment.getTitle());
        apptDescTextField.setText(selectedAppointment.getDescription());
        apptLocTextField.setText(selectedAppointment.getLocation());
        apptTypeTextField.setText(selectedAppointment.getType());

        startDatePicker.setValue(selectedAppointment.getStartTime().toLocalDate());
        endDatePicker.setValue(selectedAppointment.getEndTime().toLocalDate());

        startTimeComboBox.setItems(TimeHelper.getStartTimes());
        endTimeComboBox.setItems(TimeHelper.getEndTimes());

        try {
            contactList = ContactsDAO.getAllContacts();
            contactComboBox.setItems(contactList);
            selectedContact = ContactsDAO.getContactByID(selectedAppointment.getContactID());
            contactComboBox.setValue(selectedContact);

            customersList = CustomersDAO.getAllCustomers();
            customerIDComboBox.setItems(customersList);
            selectedCustomer = CustomersDAO.getCustomerByID(selectedAppointment.getCustomerID());
            customerIDComboBox.setValue(selectedCustomer);

            usersList = UsersDAO.getAllUsers();
            userIDComboBox.setItems(usersList);
            selectedUser = UsersDAO.getUserByID(selectedAppointment.getUserID());
            userIDComboBox.setValue(selectedUser);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
