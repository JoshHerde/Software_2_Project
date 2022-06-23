package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private static int partId = 0;
    private static int productId = 0;

    private static ObservableList<Product> theProducts = FXCollections.observableArrayList();
    private static ObservableList<Part> theParts = FXCollections.observableArrayList();

    public static ObservableList<Product> getTheProducts() {
        return theProducts;
    }
    public static ObservableList<Part> getTheParts() {
        return theParts;
    }

    public static int getNewPartId() {
        return ++partId;
    }

    public static int getNewProductId() {
        return ++productId;
    }

    public static void addProduct(Product product) {
        theProducts.add(product);
    }

    public static void addPart(Part part) {
        theParts.add(part);
    }





}


