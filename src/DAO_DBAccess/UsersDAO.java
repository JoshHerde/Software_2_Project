package DAO_DBAccess;

import Model.Customers;
import Model.Users;
import Utilities.DBConnection;
import Utilities.LoginLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class UsersDAO {

    public static Users currentUser;

    public static Users getCurrentUser() {
        return currentUser;
    }


    public static ObservableList<Users> getAllUsers() {

        ObservableList<Users> usersList = FXCollections.observableArrayList();

        try{
            String sql = "SELECT * from users";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users users = new Users(userID, userName, password);
                usersList.add(users);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return usersList;
    }

    public static boolean checkForUser(String username, String password) throws SQLException {

        try {
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                currentUser = new Users();
                currentUser.setUserName(rs.getString("User_Name"));
                currentUser.setPassword(rs.getString("Password"));
                LoginLog.inputLog(username, true);
                return true;
            }
            else {
                LoginLog.inputLog(username, false);
                return false;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Users getUserByID(int theUserID) throws SQLException {

        Users users = new Users();

        try {

            String sql = "SELECT * FROM users WHERE User_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, theUserID);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            rs.next();

            users.setUserID(rs.getInt("User_ID"));
            users.setUserName(rs.getString("User_Name"));
            users.setPassword(rs.getString("Password"));

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
}
