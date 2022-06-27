package Controller;

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

    @FXML void loginButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {
        String userName = usernameTextField.getText();
        String password = passwordTextField.getText();

        if (userName.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter Username and/or Password");
            alert.showAndWait();
        }
        else {
            currentUser = knownUser(userName, password);
            if(currentUser == null) {
                errorMessageLabel.setVisible(true);
            }
            else {
                LoginLog.inputLog(userName);

                Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
                Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userZoneId.setText(Locale.getDefault().getDisplayCountry());

        headerLabel.setText(rb.getString("headerLabel"));
        usernameTextField.setText(rb.getString("usernameTextField"));
        passwordTextField.setText(rb.getString("passwordTextField"));
        loginButton.setText(rb.getString("loginButton"));
        locationLabel.setText(rb.getString("locationLabel"));
        errorMessageLabel.setText(rb.getString("errorMessageLabel"));




        /*
        try {
            rb = ResourceBundle.getBundle("src/rb", Locale.getDefault());
            if (Locale.getDefault().getLanguage().equals("fr")) {
                headerLabel.setText(rb.getString("Login"));
                usernameTextField.setText(rb.getString("Username"));
                passwordTextField.setText(rb.getString("Password"));
                loginButton.setText(rb.getString("Login"));
                locationLabel.setText(rb.getString("Location :"));
            }
        }
        catch (MissingResourceException e) {
            System.out.println(e.getMessage());
        }
        */



    }
}
