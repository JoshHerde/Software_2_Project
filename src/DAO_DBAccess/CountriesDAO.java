package DAO_DBAccess;

import Model.Countries;
import Utilities.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesDAO {

    public static ObservableList<Countries> getAllCountries() {

        ObservableList<Countries> countryList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT * from countries";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            while(rs.next()) {
                int countryID = rs.getInt("Country_ID");
                String countryName = rs.getString("Country");
                Countries c = new Countries(countryID, countryName);
                countryList.add(c);
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return countryList;
    }

    public static void getCountryByID(int dbCountryID) {
        Countries countries = new Countries();
        try {
            String sql = "SELECT * FROM country WHERE Country_ID = ?";
            PreparedStatement ps = DBConnection.getConnection().prepareStatement(sql);

            ps.setInt(1, dbCountryID);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();

            rs.next();

            countries.setCountryID(rs.getInt("Country_ID"));
            countries.setCountryName(rs.getString("Country"));


        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
