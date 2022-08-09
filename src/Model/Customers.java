package Model;

import java.sql.Timestamp;


public class Customers {

    private int customerID;
    private String name;
    private String address;
    private String postalCode; // change to string
    private String phone;
    private String country;
    private String division;
    private int divisionID;
    private int countryID;

    public Customers(int customerID, String name, String address, String postalCode, String phone, String country, String division, int divisionID, int countryID) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.country = country;
        this.division = division;
        this.divisionID = divisionID;
        this.countryID = countryID;
    }

    public Customers(String name, String address, String postalCode, String phone, int divisionID) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
    }

    public Customers() {

    }


    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }


    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }


    public int getCountryID() {
        return countryID;
    }

    @Override
    public String toString() {
        return name;
    }
}
