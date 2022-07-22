package Controller;


import DAO_DBAccess.AppointmentsDAO;
import DAO_DBAccess.UsersDAO;
import Model.Appointments;
import Model.Customers;
import Model.Users;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentsController implements Initializable {



    @FXML private TableView<Appointments> appointmentsTable;
    @FXML private TableColumn<Appointments, Integer> idCol;
    @FXML private TableColumn<Appointments, String> titleCol;
    @FXML private TableColumn<Appointments, String> descCol;
    @FXML private TableColumn<Appointments, String> locCol;
    @FXML private TableColumn<Appointments, String> contactCol;
    @FXML private TableColumn<Appointments, String> typeCol;
    @FXML private TableColumn<Appointments, String> startCol;
    @FXML private TableColumn<Appointments, String> endCol;
    @FXML private TableColumn<Appointments, Integer> customerIdCol;
    @FXML private TableColumn<Appointments, Integer> userIdCol;
    @FXML private ToggleGroup appointmentsToggleGroup;
    @FXML private RadioButton allRadioButton;
    @FXML private  RadioButton monthRadioButton;
    @FXML private  RadioButton weekRadioButton;
    @FXML private  TextField searchTextField;

    public static ObservableList<Appointments> allAppointments;
    public static ObservableList<Customers> allCustomers;

    private static Appointments selectedAppointment;

    public static Appointments getSelectedAppointment() {
        return selectedAppointment;
    }




    @FXML void allRbClicked(ActionEvent actionEvent) {
        appointmentsTable.setItems(AppointmentsDAO.getAllAppointments());
    }

    @FXML void monthRbClicked(ActionEvent actionEvent) {
        appointmentsTable.setItems(AppointmentsDAO.getMonthlyAppointments());
    }

    @FXML void weekRbClicked(ActionEvent actionEvent) {
        appointmentsTable.setItems(AppointmentsDAO.getWeeklyAppointments());
    }

    @FXML void newAppointmentClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/AddAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void editAppointmentClicked(ActionEvent actionEvent) throws IOException{
        selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please select an appointment to Edit.");
            alert.showAndWait();

        }
        Parent root = FXMLLoader.load(getClass().getResource("/View/EditAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML void deleteAppointmentClicked(ActionEvent actionEvent) {
        selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();

        if(selectedAppointment == null) {
        }

        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Confirm");
            alert.setHeaderText("Are you sure you want to delete this Appointment?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    AppointmentsDAO.deleteAppointment(selectedAppointment.getAppointmentID());

                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Appointment deleted");
                alert1.setHeaderText("Your appointment was successfully deleted.");
                alert1.showAndWait();

                if (allRadioButton.isSelected()) {
                    try {
                        appointmentsTable.setItems(AppointmentsDAO.getAllAppointments());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
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

    public void appointmentSearchClicked(ActionEvent actionEvent) {
        String searchString = searchTextField.getText();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appointmentsTable.setItems(AppointmentsDAO.getAllAppointments());
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userID"));

    }
}
