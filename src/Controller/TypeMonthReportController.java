package Controller;

import DAO_DBAccess.AppointmentsDAO;
import Model.Appointments;
import Model.TypeMonthReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

public class TypeMonthReportController  implements Initializable {

    @FXML private TableView<TypeMonthReport> typeMonthTable;

    @FXML private TableColumn<TypeMonthReport, String> typeCol;

    @FXML private TableColumn<TypeMonthReport, Month> monthCol;

    @FXML private TableColumn<TypeMonthReport, Integer> totalCol;


    @FXML void backButtonClicked(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/Reports.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            ObservableList<String> types = FXCollections.observableArrayList();
            ObservableList<Month> months = FXCollections.observableArrayList();
            ObservableList<Appointments> databaseAppts = AppointmentsDAO.getAllAppointments();
            ObservableList<TypeMonthReport> reportList = FXCollections.observableArrayList();

            databaseAppts.forEach(appointments -> {
                String type = appointments.getType();
                if (!types.contains(type)) {
                    types.add(type);
                }
            });

            databaseAppts.forEach(appointments -> {
                Month month = appointments.getStartTime().getMonth();
                if (!months.contains(month)) {
                    months.add(month);
                }
            });

            String type;
            Month month;
            Appointments appointments;
            int typeCount;

            for (int i = 0; i < types.size(); i++) {
                type = types.get(i);
                typeCount = 0;

                for (int j = 0; j < months.size(); j++) {
                    month = months.get(j);

                    for (int k = 0; k < databaseAppts.size(); k++) {
                        appointments = databaseAppts.get(k);

                        if (appointments.getType().equals(type) && appointments.getStartTime().getMonth().equals(month)) {
                            typeCount++;
                        }
                    }

                    if (typeCount > 0) {
                        TypeMonthReport report = new TypeMonthReport(type, month, typeCount);
                        reportList.add(report);
                    }
                }
            }
            typeMonthTable.setItems(reportList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
