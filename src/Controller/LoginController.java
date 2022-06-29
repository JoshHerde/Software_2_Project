package Controller;

import DAO_DBAccess.UsersDAO;
import Model.Users;
import Utilities.LoginLog;
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
import java.time.ZonedDateTime;
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

    public static Users currentUser;

    public static Users getCurrentUser() {
        return currentUser;
    }


    @FXML void loginButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter Username and/or Password");
            alert.showAndWait();
        }
        boolean knownUser = UsersDAO.checkForUser(username, password);
        if (knownUser) {
            UsersDAO.checkForUser(username, password);
            LoginLog.inputLog(usernameTextField.getText() + " has successfully logged in at " + ZonedDateTime.now() + " ");

            Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            errorMessageLabel.setVisible(true);
            LoginLog.inputLog(usernameTextField.getText() + " has been denied access at " + ZonedDateTime.now() + " ");
        }
    }




    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userZoneId.setText(Locale.getDefault().getDisplayCountry());

        try {
            rb = ResourceBundle.getBundle("src/rb", Locale.getDefault());

            if (Locale.getDefault().getLanguage().equals("fr") || Locale.getDefault().getLanguage().equals("en")) {

                headerLabel.setText(rb.getString("Login"));
                usernameTextField.setText(rb.getString("Username"));
                passwordTextField.setText(rb.getString("Password"));
                loginButton.setText(rb.getString("Login"));
                locationLabel.setText(rb.getString("Location :"));
                errorMessageLabel.setText(rb.getString("Invalid Username or Password"));
            }
        }
        catch (MissingResourceException e) {
            System.out.println(e.getMessage());
        }
    }
}
