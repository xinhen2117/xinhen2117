import java.util.ArrayList;
import java.util.*;

public class Customer extends User {
    private ShoppingCart shoppingCart;
    private List<Order> orders;
    private String password;
    public Customer(int userId, String name, String email,String password) {
        super(userId, name, email,password);
        this.shoppingCart = new ShoppingCart();
        this.orders = new ArrayList<>();
    }

    public void addToCart(Product product, int quantity) {
        shoppingCart.addItem(product, quantity);
    }

    public void removeFromCart(Product product) {
        shoppingCart.removeItem(product);
    }

    public void placeOrder() {
        if (!shoppingCart.isEmpty()) {
            Order order = new Order(this, new Date(), shoppingCart.getItems(), shoppingCart.calculateTotal());
            orders.add(order);
            shoppingCart.clear();
            System.out.println("Order placed successfully.");
        } else {
            System.out.println("Shopping cart is empty. Cannot place an empty order.");
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public String getPassword() {
        return password;
    }
}
