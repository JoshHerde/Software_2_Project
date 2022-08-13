package Model;

import java.time.Month;

public class TypeMonthReport {

    private String apptType;

    private Month month;

    private int total;

    public TypeMonthReport() {

    }

    public TypeMonthReport(String apptType, Month month, int total) {
        this.apptType = apptType;
        this.month = month;
        this.total = total;
    }

    public String getApptType() {
        return apptType;
    }

    public void setApptType(String apptType) {
        this.apptType = apptType;
    }


    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
