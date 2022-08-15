package Utilities;

import DAO_DBAccess.AppointmentsDAO;
import Model.Appointments;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.*;

public class ValidAppointment {

    public static boolean missingFields(Appointments appointments) {
        boolean emptyFields = false;

        if (appointments.getTitle().equals("")) {
            emptyFields = true;
        }
        else if (appointments.getDescription().equals("")) {
            emptyFields = true;
        }
        else if (appointments.getLocation().equals("")) {
            emptyFields = true;
        }
        else if (appointments.getType().equals("")) {
            emptyFields = true;
        }

        if (emptyFields) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Make sure all text fields are filled out");
            alert.showAndWait();
        }
        return emptyFields;
    }

    public static boolean Overlapping(Appointments newAppointment) throws SQLException {
        ObservableList<Appointments> allAppointmentList = AppointmentsDAO.getAllAppointments();
        boolean overlapping = false;
        Appointments Overlapping = new Appointments();

        for (Appointments appointments : allAppointmentList) {
            if ( appointments.getCustomerID() != newAppointment.getCustomerID() || appointments.getAppointmentID() == newAppointment.getAppointmentID()) {
                continue;
            }

            //int newApptCustomerID = newAppointment.getCustomerID();
            LocalDateTime newApptStart = newAppointment.getStartTime();
            LocalDateTime newApptEnd = newAppointment.getEndTime();

            //int apptCustomerID = appointments.getCustomerID();
            LocalDateTime apptStart = appointments.getStartTime();
            LocalDateTime apptEnd = appointments.getEndTime();

            Overlapping = appointments;

            if (newApptStart.isAfter(apptStart) && newApptStart.isBefore(apptEnd)) {
                overlapping = true;
                break;

            } else if (newApptEnd.isAfter(apptStart) && newApptEnd.isBefore(apptEnd)) {
                overlapping = true;
                break;

            } else if (newApptStart.isBefore(apptStart) && newApptEnd.isAfter(apptEnd)) {
                overlapping = true;
                break;

            } else if (newApptStart.isEqual(apptStart) || newApptEnd.isEqual(apptEnd)) {
                overlapping = true;
                break;

            } else if (newApptStart.isAfter(apptStart) && newApptEnd.isBefore(apptEnd)) {
                overlapping = true;
                break;
            }
        }
        return overlapping;
    }

    public static boolean startAfterEnd(Appointments appointments) {
        boolean startAfterEnd = false;
        LocalDateTime startTime = appointments.getStartTime();
        LocalDateTime endTime = appointments.getEndTime();

        if (startTime.isAfter(endTime)) {
            startAfterEnd = true;
        }

        if (startAfterEnd) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Make sure start time is before end time.");
            alert.showAndWait();
        }
        return startAfterEnd;
    }

    public static boolean orgHours (Appointments appointments) {

        LocalDateTime startDateTime = appointments.getStartTime();
        LocalDate startDate = startDateTime.toLocalDate();
        ZonedDateTime startTimeZone = startDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime startTimeEST = startTimeZone.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalTime businessOpen = LocalTime.parse("08:00:00");
        LocalDateTime businessStartDateTime = LocalDateTime.of(startDate, businessOpen);
        ZonedDateTime businessStartTimeZone = businessStartDateTime.atZone(ZoneId.of("America/New_York"));

        LocalDateTime endDateTime = appointments.getEndTime();
        ZonedDateTime endTimeZone = endDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime endTimeEST = endTimeZone.withZoneSameInstant(ZoneId.of("America/New_York"));

        LocalTime businessClosed = LocalTime.parse("22:00:00");
        LocalDateTime businessEndDateTime = LocalDateTime.of(startDate, businessClosed);
        ZonedDateTime businessEndTimeZone = businessEndDateTime.atZone(ZoneId.of("America/New_York"));

        boolean orgClosed = startTimeEST.isBefore(businessStartTimeZone) || endTimeEST.isAfter(businessEndTimeZone);

        if (orgClosed) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("The appointment time you chose is outside of the organization's hours. \nHours are 8:00 - 22:00 EST.");
            alert.showAndWait();
        }
        return orgClosed;
    }

}
