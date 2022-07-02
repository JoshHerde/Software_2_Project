package DAO_DBAccess;

import Model.Customers;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CustomersDAO {

    public static ObservableList<Customers> getAllCustomers() {

        ObservableList<Customers> customersList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from customers AS c INNER JOIN first_level_divisions AS d ON c.Division_ID = d.Division_ID INNER JOIN countries AS co ON co.country_ID = d.Country_ID ";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Customers customers = new Customers(
                    rs.getInt("Customer_ID"),
                    rs.getString("Customer_Name"),
                    rs.getString("Address"),
                    rs.getString("Postal_Code"),
                    rs.getString("Phone"),
                    rs.getInt("Division_ID")
                );
                customersList.add(customers);
            }
            return customersList;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public static void addCustomer(Customers customer) throws SQLException {
        String sql = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Create_Date, Created_By, Last_Update, Last_Updated_By, Division_ID)" +
                "VALUES(?, ?, ?, ?, NOW(), ?, NOW(), ?, ?)";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(7, customer.getDivisionID());

        ps.executeUpdate();

    }

    public static void editCustomer(Customers customer) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Last_Update = NOW(), Last_Updated_By = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

        ps.setString(1, customer.getName());
        ps.setString(2, customer.getAddress());
        ps.setString(3, customer.getPostalCode());
        ps.setString(4, customer.getPhone());
        ps.setInt(6, customer.getDivisionID());
        ps.setInt(7, customer.getCustomerID());

        ps.execute();

    }
}
