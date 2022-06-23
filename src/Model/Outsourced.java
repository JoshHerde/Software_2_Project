package Model;

public class Outsourced extends Part {

    private String companyName;

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * The getter for companyName
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * The setter for companyName
     * @param companyName
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
