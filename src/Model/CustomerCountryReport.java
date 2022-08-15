package Model;

import java.time.Month;

public class CustomerCountryReport {

    private String reportCountry;

    private int customers;

    private int total;

    public CustomerCountryReport() {

    }

    public CustomerCountryReport(String reportCountry, int customers, int total) {
        this.reportCountry = reportCountry;
        this.customers = customers;
        this.total = total;
    }

    public String getReportCountry() {
        return reportCountry;
    }

    public void setReportCountry(String reportCountry) {
        this.reportCountry = reportCountry;
    }


    public int getCustomers() {
        return customers;
    }

    public void setCustomers(int customers) {
        this.customers = customers;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
