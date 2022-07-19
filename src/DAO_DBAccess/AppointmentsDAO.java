package DAO_DBAccess;

import Model.Appointments;
import Model.Users;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentsDAO {

    public static ObservableList<Appointments> getAllAppointments() {
        ObservableList<Appointments> allAppointmentList = FXCollections.observableArrayList();

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
                allAppointmentList.add(appointments);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allAppointmentList;

    }

    public static ObservableList<Appointments> getMonthlyAppointments() {
        ObservableList<Appointments> monthlyAppointments = FXCollections.observableArrayList();

        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDate oneMonth = now.plusMonths(1);

        try {
            String sql = "SELECT * FROM appointments WHERE Start < ? AND Start > ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setDate(1, Date.valueOf(oneMonth));
            ps.setDate(2, Date.valueOf(now));
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
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
                monthlyAppointments.add(appointments);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return monthlyAppointments;
    }

    public static ObservableList<Appointments> getWeeklyAppointments() {
        ObservableList<Appointments> weeklyAppointmentList = FXCollections.observableArrayList();

        LocalDate now = LocalDateTime.now().toLocalDate();
        LocalDate oneWeek = now.plusDays(7);

        try {
            String sql = "SELECT * FROM appointments WHERE Start < ? AND Start > ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setDate(1, Date.valueOf(oneWeek));
            ps.setDate(2, Date.valueOf(now));
            ps.executeQuery();

            ResultSet rs = ps.getResultSet();
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
                weeklyAppointmentList.add(appointments);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return weeklyAppointmentList;
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
