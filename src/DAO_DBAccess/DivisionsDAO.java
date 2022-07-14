package DAO_DBAccess;

import Model.Divisions;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionsDAO {

    public ObservableList<Divisions> getAllDivisions() {

        ObservableList<Divisions> divisionsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return divisionsList;
    }

    public static int addDivision(String divisionName, int countryID) throws SQLException {
        String sql = "INSERT INTO first_level_divisions (Division, Country_ID) VALUES(?, ?)";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, divisionName);
        ps.setInt(2, countryID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }
}
