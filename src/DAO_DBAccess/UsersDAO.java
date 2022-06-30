package DAO_DBAccess;

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
            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                int userID = rs.getInt("User_ID");
                String userName = rs.getString("User_Name");
                String password = rs.getString("Password");
                Users u = new Users(userID, userName, password);
                usersList.add(u);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }

    public static boolean checkForUser(String username, String password) throws SQLException {

        try {
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.execute();
            ResultSet rs = ps.getResultSet();

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
        catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
