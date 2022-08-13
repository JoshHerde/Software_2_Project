package Main;

import Utilities.DBConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;


public class Main extends Application {



    public static void main(String[] args) {

        DBConnection.openConnection();
        launch(args);
        DBConnection.closeConnection();

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Login.fxml")));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}



// todo fix reports. make them all in table view. for contact schedule put combo box on table view to switch between customers.
// todo fix business hours. add to valid appointments utility.
// todo make alert for empty date or time !!!!!!!!
//when deleting customer, customer wont fully remove until i exit customer screen.
//ask about reports controller



