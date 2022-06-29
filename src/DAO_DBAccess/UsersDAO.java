package DAO_DBAccess;

import Model.Users;
import Utilities.DBConnection;
import Utilities.DBQuery;
import Utilities.LoginLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.sql.*;

public class UsersDAO {


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
                Timestamp createdDate = rs.getTimestamp("Create_Date");
                String createdBy = rs.getString("Created_By");
                Timestamp lastUpdated = rs.getTimestamp("Last_Updated");
                String lastUpdatedBy = rs.getString("Last_Updated_By");
                Users u = new Users(userName, password, createdDate, createdBy, lastUpdated, lastUpdatedBy);
                usersList.add(u);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }

    public static boolean checkForUser(String username, String password) {

        Users user = new Users();

        try {
            String sql = "SELECT * FROM users WHERE User_Name = ? AND Password = ?";
            PreparedStatement ps = DBConnection.connection.prepareStatement(sql);
            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                user.setUserName(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
                LoginLog.inputLog(username);
                return true;
            }
            else {
                LoginLog.inputLog(username);
                return false;
            }
        }
        catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
