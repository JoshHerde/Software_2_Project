package Controller;

import DAO_DBAccess.AppointmentsDAO;
import DAO_DBAccess.UsersDAO;
import Model.Appointments;
import Model.Users;
import Utilities.LoginLog;
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

import javax.swing.text.html.ObjectView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private Label headerLabel;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Label locationLabel;
    @FXML private Label userZoneId;
    @FXML private Button loginButton;
    @FXML private Label errorMessageLabel;

    public static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MM-dd-YYYY HH:mm");

    private static Users currentUser;

    public static Users getCurrentUser() {
        return currentUser;
    }


    @FXML void loginButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        currentUser = UsersDAO.checkForUser(username, password);


        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter Username and/or Password");
            alert.showAndWait();
        }
        if (currentUser != null) {
            LoginLog.inputLog(username, true);
            upcomingAppointment();

            Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            LoginLog.inputLog(username, false);
            errorMessageLabel.setVisible(true);
        }
    }

    private void upcomingAppointment() throws SQLException {
        try {
            boolean upcomingAppointment = false;
            ObservableList<Appointments> dataBaseAppts = AppointmentsDAO.getAllAppointments();
            LocalDateTime now = LocalDateTime.now();
            int currentUserID = currentUser.getUserID();

            for (Appointments appointments : dataBaseAppts) {
                LocalDateTime startTime = appointments.getStartTime();

                if ((appointments.getUserID() == currentUserID) && startTime.isAfter(now) && startTime.isBefore(now.plusMinutes(15))) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Just be aware...");
                    alert.setHeaderText("Your appointment with ID: " + appointments.getAppointmentID() + " is starting at " + startTime.format(dateTimeFormat) + ".");
                    alert.showAndWait();

                    upcomingAppointment = true;
                    break;
                }
            }
            if (!upcomingAppointment) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Just be aware...");
                alert.setHeaderText("You have no appointments anytime soon!");
                alert.showAndWait();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userZoneId.setText(ZoneId.systemDefault().toString());

        try {
            rb = ResourceBundle.getBundle("rb", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {

                headerLabel.setText(rb.getString("headerLabel"));
                usernameTextField.setText(rb.getString("usernameTextField"));
                passwordTextField.setText(rb.getString("passwordTextField"));
                loginButton.setText(rb.getString("loginButton"));
                locationLabel.setText(rb.getString("locationLabel"));
                errorMessageLabel.setText(rb.getString("errorMessageLabel"));

            }
        }
        catch (MissingResourceException e) {
            System.out.println(e.getMessage());
        }
    }
}
