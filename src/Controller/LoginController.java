package Controller;

import DAO_DBAccess.UsersDAO;
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
import java.time.ZoneId;
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


    @FXML void loginButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();

        boolean knownUser = UsersDAO.checkForUser(username, password);


        if (username.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please enter Username and/or Password");
            alert.showAndWait();
        }
        if (knownUser) {
            boolean isFound = true;

            Parent root = FXMLLoader.load(getClass().getResource("/view/Appointments.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {

            errorMessageLabel.setVisible(true);

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
