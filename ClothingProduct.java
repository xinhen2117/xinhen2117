import java.util.*;
class ClothingProduct extends Product {
    private String size;
    private String material;

    public ClothingProduct(int productId, String name, double price, int stockQuantity, String size, String material) {
        super(productId, name, price, stockQuantity);
        this.size = size;
        this.material = material;
    }

    // Additional methods for ClothingProduct
}
