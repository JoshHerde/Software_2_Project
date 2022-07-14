package DAO_DBAccess;

import Model.Contacts;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsDAO {

    public ObservableList<Contacts> getAllContacts() {

        ObservableList<Contacts> contactList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from contacts";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int contactID = rs.getInt("Contact_ID");
                String contactName = rs.getString("Contact_Name");
                String contactEmail = rs.getString("Email");
                Contacts contacts = new Contacts(contactID, contactName, contactEmail);
                contactList.add(contacts);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return contactList;
    }

    public static void addContact(Contacts contacts) {
        try {
            String sql = "INSERT INTO contacts (Contact_Name, Email) VALUES(?, ?)";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setString(1, contacts.getContactName());
            ps.setString(2, contacts.getContactEmail());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
