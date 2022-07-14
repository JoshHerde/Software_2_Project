package DAO_DBAccess;

import Model.Appointments;
import Model.Users;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;

public class AppointmentsDAO {

    public static ObservableList<Appointments> getAllAppointments() {

        ObservableList<Appointments> appointmentList = FXCollections.observableArrayList();


        try {
            String sql = "SELECT * from appointments AS a INNER JOIN contacts AS c ON a.Contact_ID = c.Contact_ID";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int appointmentID = rs.getInt("Appointment_ID");
                String title = rs.getString("Title");
                String description = rs.getString("Description");
                String location = rs.getString("Location");
                String type = rs.getString("Type");
                LocalDateTime startTime = rs.getTimestamp("Start").toLocalDateTime();
                LocalDateTime endTime = rs.getTimestamp("End").toLocalDateTime();
                int customerID = rs.getInt("Customer_ID");
                int userID = rs.getInt("User_ID");
                int contactID = rs.getInt("Contact_ID");
                Appointments appointments = new Appointments(appointmentID, title, description, location, type,
                                                                startTime, endTime, customerID, userID, contactID);
                appointmentList.add(appointments);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return appointmentList;

    }


    public static void addAppointment(Appointments appointments) {
        try {
            String sql = "INSERT INTO appointments (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, appointments.getTitle());
            ps.setString(2,appointments.getDescription());
            ps.setString(3, appointments.getLocation());
            ps.setString(4, appointments.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointments.getStartTime()));
            ps.setTimestamp(6,Timestamp.valueOf(appointments.getEndTime()));
            ps.setInt(7, appointments.getCustomerID());
            ps.setInt(8, appointments.getUserID());
            ps.setInt(9, appointments.getContactID());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void editAppointment(Appointments appointments) {
        try {
            String sql = "UPDATE appointments SET (Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, appointments.getTitle());
            ps.setString(2,appointments.getDescription());
            ps.setString(3, appointments.getLocation());
            ps.setString(4, appointments.getType());
            ps.setTimestamp(5, Timestamp.valueOf(appointments.getStartTime()));
            ps.setTimestamp(6,Timestamp.valueOf(appointments.getEndTime()));
            ps.setInt(7, appointments.getCustomerID());
            ps.setInt(8, appointments.getUserID());
            ps.setInt(9, appointments.getContactID());
            ps.setInt(10, appointments.getAppointmentID());

            ps.executeUpdate();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteAppointment(int dbAppointmentID) {
        try {
            String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, dbAppointmentID);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
