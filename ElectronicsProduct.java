import java.util.*;
class ElectronicsProduct extends Product {
    private int warrantyPeriod;
    private int voltage;

    public ElectronicsProduct(int productId, String name, double price, int stockQuantity, int warrantyPeriod, int voltage) {
        super(productId, name, price, stockQuantity);
        this.warrantyPeriod = warrantyPeriod;
        this.voltage = voltage;
    }

    // Additional methods for ElectronicsProduct
}