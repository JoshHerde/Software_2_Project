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

    public static Divisions getDivisionName(int dbDivisionID) throws SQLException {

        Divisions divisions = new Divisions();

        try {
            String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, dbDivisionID);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            rs.next();

            divisions.setDivisionID(rs.getInt("Division_ID"));
            divisions.setDivisionName(rs.getString("Division"));
            divisions.setCountryID(rs.getInt("Country_ID"));

            }
            catch (SQLException e) {
            e.printStackTrace();
            }
        return divisions;
    }

    public static int getDivisionID(String selectedDivision) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division = ?";
        PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
        ps.setString(1, selectedDivision);

        try {
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                return rs.getInt("Division_ID");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Didn't get ID");
            return -1;
        }
        return -1;
    }
}
