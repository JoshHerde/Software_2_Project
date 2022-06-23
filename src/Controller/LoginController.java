package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public Label headerLabel;
    public TextField usernameTextField;
    public PasswordField passwordTextField;
    public Label locationLabel;
    public Label userLocationLabel;
    public Button loginButton;

    public void loginButtonClicked(ActionEvent actionEvent) throws ClassNotFoundException, IOException {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
