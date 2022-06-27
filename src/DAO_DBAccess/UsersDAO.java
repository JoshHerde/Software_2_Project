package DAO_DBAccess;

import Model.Countries;
import Model.Users;
import Utilities.DBConnection;
import Utilities.DBQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public static Users currentUser(Users user){
        String getStatement = "Select User_ID,User_Name,Password from users where lower(User_Name) = ? and lower(Password) = ?;";

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.setString(1, user.getUserName().toLowerCase());
            ps.setString(2, user.getPassword().toLowerCase());
            ps.execute();
            ResultSet rs = ps.getResultSet();
            if (rs.next()){
                return new Users(rs.getInt("User_ID"), rs.getString("User_Name"), rs.getString("Password"));
            }


        }catch (SQLException sqlException){
            sqlException.printStackTrace();

        }
        return null;
    }


}
