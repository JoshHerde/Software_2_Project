package DAO_DBAccess;

import Model.Divisions;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DivisionsDAO {

    public static ObservableList<Divisions> getAllDivisions() throws SQLException {

        ObservableList<Divisions> divisionsList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from first_level_divisions";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                int divisionID = rs.getInt("Division_ID");
                String divisionName = rs.getString("Division");
                int countryID = rs.getInt("Country_ID");
                Divisions divisions = new Divisions(divisionID, divisionName, countryID);
                divisionsList.add(divisions);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return divisionsList;
    }

    public static int getDivisionID(int dbDivisionID) throws SQLException {

        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

        ps.setInt(1, dbDivisionID);
        ps.executeQuery();

        try {
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                return rs.getInt("Division_ID");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return dbDivisionID;
    }
}
