package Controller;

import DAO_DBAccess.AppointmentsDAO;
import DAO_DBAccess.CountriesDAO;
import DAO_DBAccess.CustomersDAO;
import Model.Appointments;
import Model.Countries;
import Model.Customers;
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
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;

public class ReportsController implements Initializable {


    @FXML private RadioButton typeRadioButton;
    @FXML private RadioButton monthRadioButton;
    @FXML private ToggleGroup TypeMonthToggleGroup;
    @FXML private Button GoButton1;

    @FXML private Button GoButton2;

    @FXML private Button GoButton3;


    @FXML void backButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Appointments.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML void goButton1Clicked(ActionEvent actionEvent) {
        ObservableList<String> typeList = FXCollections.observableArrayList();

        ObservableList<Integer> January = FXCollections.observableArrayList();
        ObservableList<Integer> February = FXCollections.observableArrayList();
        ObservableList<Integer> March = FXCollections.observableArrayList();
        ObservableList<Integer> April = FXCollections.observableArrayList();
        ObservableList<Integer> May = FXCollections.observableArrayList();
        ObservableList<Integer> June = FXCollections.observableArrayList();
        ObservableList<Integer> July = FXCollections.observableArrayList();
        ObservableList<Integer> August = FXCollections.observableArrayList();
        ObservableList<Integer> September = FXCollections.observableArrayList();
        ObservableList<Integer> October = FXCollections.observableArrayList();
        ObservableList<Integer> November = FXCollections.observableArrayList();
        ObservableList<Integer> December = FXCollections.observableArrayList();

        try {
            ObservableList<Appointments> appointmentsList = AppointmentsDAO.getAllAppointments();

            if (appointmentsList != null) {
                for (Appointments appointments : appointmentsList) {
                    String type = appointments.getType();
                    LocalDate month = appointments.getStartTime().toLocalDate();


                    if (month.getMonth().equals(Month.of(1))) {
                        January.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(2))) {
                        February.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(3))) {
                        March.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(4))) {
                        April.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(5))) {
                        May.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(6))) {
                        June.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(7))) {
                        July.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(8))) {
                        August.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(9))) {
                        September.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(10))) {
                        October.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(11))) {
                        November.add(month.getMonthValue());
                    }

                    if (month.getMonth().equals(Month.of(12))) {
                        December.add(month.getMonthValue());
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        if (typeRadioButton.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointments by Type");
            alert.setHeaderText("Total number of Appointments by Type: ");
            alert.setContentText("");
            alert.setResizable(true);
            alert.showAndWait();
        }

        if (monthRadioButton.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Appointments by Month");
            alert.setHeaderText("Total number of Appointments by Month: ");
            alert.setContentText("\nJanuary: " + January.size() +
                    "\nFebruary: " + February.size() +
                    "\nMarch: " + March.size() +
                    "\nApril: " + April.size() +
                    "\nMay: " + May.size() +
                    "\nJune: " + June.size() +
                    "\nJuly: " + July.size() +
                    "\nAugust: " + August.size() +
                    "\nSeptember: " + September.size() +
                    "\nOctober: " + October.size() +
                    "\nNovember: " + November.size() +
                    "\nDecember: " + December.size());
            alert.showAndWait();
        }
    }

    @FXML void goButton2Clicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/ContactScheduleReport.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML void goButton3Clicked(ActionEvent actionEvent) {
        ObservableList<String> USCustomerList = FXCollections.observableArrayList();
        ObservableList<String> UKCustomerList = FXCollections.observableArrayList();
        ObservableList<String> CanadaCustomerList = FXCollections.observableArrayList();

        try {
            ObservableList<Customers> customersList = CustomersDAO.getAllCustomers();

            if (customersList != null) {
                for (Customers customers : customersList) {
                    String country = customers.getCountry();

                    if (country.equals("U.S")) {
                        USCustomerList.add(country);
                    }

                    if (country.equals("U.K")) {
                        UKCustomerList.add(country);
                    }

                    if (country.equals("Canada")) {
                        CanadaCustomerList.add(country);
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customers by Country");
        alert.setHeaderText("Total number of Customers by Country: ");
        alert.setContentText("\nU.S: " + USCustomerList.size() +
                            "\nU.K: " + UKCustomerList.size() +
                            "\nCanada: " + CanadaCustomerList.size());
        alert.setResizable(true);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
