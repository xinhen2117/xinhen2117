import jdk.internal.icu.text.UnicodeSet;

import java.util.List;

public class Admin extends User {
    private String password;

    public Admin(int userId, String name, String email, String password) {
        super(userId, name, email,password);
        this.password = password;
    }

    public void addProduct(Product product, List<Product> products) {
        products.add(product);
        System.out.println("Product added successfully.");
    }


    public void updateProductStock(Product product, int newStock) {
        if (newStock >= 0) {
            product.setStockQuantity(newStock);
            System.out.println("Product stock updated successfully.");
        } else {
            System.out.println("Invalid stock quantity.");
        }
    }

    public void removeProduct(Product product, List<Product> products) {
        if (products.contains(product)) {
            products.remove(product);
            System.out.println("Product removed successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    public void removeCustomer(Customer customer, List<Customer> customers) {
        if (customers.contains(customer)) {
            customers.remove(customer);
            System.out.println("Customer removed successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
