import java.util.*;
class ShoppingCart {
    private List<Item> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Product product, int quantity) {
        // Check if the product is already in the cart
        for (Item item : items) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                item.setQuantity(item.getQuantity() + quantity);
                System.out.println(quantity + " " + product.getName() + "(s) added to the cart.");
                return;
            }
        }

        // If not, add it as a new item
        items.add(new Item(product, quantity));
        System.out.println(quantity + " " + product.getName() + "(s) added to the cart.");
    }

    public void removeItem(Product product) {
        // Remove the product from the cart
        for (Item item : items) {
            if (item.getProduct().getProductId() == product.getProductId()) {
                items.remove(item);
                System.out.println(product.getName() + " removed from the cart.");
                return;
            }
        }
        System.out.println(product.getName() + " not found in the cart.");
    }

    public void clear() {
        items.clear();
        System.out.println("Cart cleared.");
    }
    
    public List<Item> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
