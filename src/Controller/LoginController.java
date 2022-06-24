package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML private Label headerLabel;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;
    @FXML private Label locationLabel;
    @FXML private Label userZoneId;
    @FXML private Button loginButton;

    @FXML void loginButtonClicked(ActionEvent actionEvent) throws IOException, SQLException {


    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userZoneId.setText(Locale.getDefault().getDisplayCountry());




    }
}
