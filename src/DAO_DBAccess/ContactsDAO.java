package DAO_DBAccess;

import Model.Contacts;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsDAO {

    public static ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contacts newContact = new Contacts(contactID, contactName, contactEmail);
                contactList.add(newContact);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contactList;
    }

    public static Contacts getContactNameByID(int theContactID) throws SQLException {

        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setInt(1, theContactID);
        ps.executeQuery();

        try {
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contacts newContact = new Contacts(contactID, contactName, contactEmail);
                return newContact;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Contacts getContactByID(String nameOfContact) throws SQLException {

        String sql = "SELECT * FROM contacts WHERE Contact_Name = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setString(1, nameOfContact);
        ps.executeQuery();

        try {
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contacts newContact = new Contacts(contactID, contactName, contactEmail);
                return newContact;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
