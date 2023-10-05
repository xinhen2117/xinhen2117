import java.util.*;
class Order {
    private int orderId;
    private Customer customer;
    private Date orderDate;
    private List<Item> items;
    private double totalAmount;

    public Order(Customer customer, Date orderDate, List<Item> items, double v) {
        this.orderId = generateOrderId();
        this.customer = customer;
        this.orderDate = orderDate;
        this.items = items;
    }

    private int generateOrderId() {
        // Implement logic to generate a unique order ID, such as using a random number or timestamp
        return new Random().nextInt(1000) + 1;
    }

    public void generateInvoice() {
        if (customer.getShoppingCart().getItems().isEmpty()) {
            System.out.println("No items in the cart. Invoice not generated.");
            return;
        }
        totalAmount = customer.getShoppingCart().calculateTotal();
        System.out.println("Invoice generated for Order #" + orderId);
        System.out.println("Total Amount: $" + totalAmount);
    }
//    public void placeOrder() {
//        ShoppingCart shoppingCart = null;
//        if (!shoppingCart.isEmpty()) {
//            Order order = new Order(this, new Date(), shoppingCart.getItems(), shoppingCart.calculateTotal());
//            orders.add(order);
//            shoppingCart.clear();
//            System.out.println("Order placed successfully.");
//            order.generateInvoice(); // Generate invoice for the placed order
//        } else {
//            System.out.println("Shopping cart is empty. Cannot place an empty order.");
//        }
//    }

}
