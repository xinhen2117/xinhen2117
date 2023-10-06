import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 初始化商品列表
        List<Product> products = loadProductsFromFile("C:/Users/xinhen17/IdeaProjects/exam1/src/products.txt");

        // 初始化管理员和用户
        Admin admin = new Admin(1001, "xinhen", "2501577489@qq.com", "2117");
        List<Customer> customers = loadCustomersFromFile("C:/Users/xinhen17/IdeaProjects/exam1/src/customers.txt");

        // 登录
        boolean isLoggedIn = false;
        User currentUser = null;
        while (!isLoggedIn) {
            System.out.println("Login:");
            System.out.print("Enter username: ");
            String username = scanner.next();
            System.out.print("Enter password: ");
            String password = scanner.next();

            if (username.equals(admin.getName()) && password.equals(admin.getPassword())) {
                isLoggedIn = true;
                currentUser = admin;
                System.out.println("Admin login successful.");
            } else {
                for (Customer customer : customers) {
                    if (username.equals(customer.getName()) && password.equals(customer.getPassword())) {
                        isLoggedIn = true;
                        currentUser = customer;
                        System.out.println("Customer login successful.");
                        break;
                    }
                }
                if (!isLoggedIn) {
                    System.out.println("Invalid username or password. Please try again.");
                }
            }
        }

        int productId = Integer.parseInt(null); // 商品ID，默认值为-1

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Add Product to Cart");
            System.out.println("2. Remove Product from Cart");
            System.out.println("3. Place Order");
            if (currentUser instanceof Admin) {
                System.out.println("4. Add Product (Admin)");
                System.out.println("5. Update Product Stock (Admin)");
            }
            System.out.println("6. View Products");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter product ID: ");
                    productId = scanner.nextInt();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    Product product = getProductById(products, productId);
                    if (product != null && currentUser instanceof Customer) {
                        Customer customer = (Customer) currentUser;
                        customer.addToCart(product, quantity);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 2:
                    System.out.print("Enter product ID: ");
                    productId = scanner.nextInt();
                    product = getProductById(products, productId);
                    if (product != null && currentUser instanceof Customer) {
                        Customer customer = (Customer) currentUser;
                        customer.removeFromCart(product);
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 3:
                    if (currentUser instanceof Customer) {
                        Customer customer = (Customer) currentUser;
                        customer.placeOrder();
                    }
                    break;
                case 4:
                    if (currentUser instanceof Admin) {
                        System.out.print("Enter product name: ");
                        String productName = scanner.next();
                        System.out.print("Enter product price: ");
                        double productPrice = scanner.nextDouble();
                        System.out.print("Enter initial stock quantity: ");
                        int stockQuantity = scanner.nextInt();
                        productId++; // 自增，用于示例商品ID
                        Product Product = new Product(productId, productName, productPrice, stockQuantity);
                        admin.addProduct(new Product(productId, productName, productPrice, stockQuantity), products);
                        products.add(Product); // 将新商品添加到商品列表
                        saveProductsToFile(products, "products.txt"); // 保存商品信息到文件
                    } else {
                        System.out.println("Access denied. You are not an admin.");
                    }
                    break;
                case 5:
                    if (currentUser instanceof Admin) {
                        System.out.print("Enter product ID: ");
                        productId = scanner.nextInt();
                        product = getProductById(products, productId);
                        if (product != null) {
                            System.out.print("Enter new stock quantity: ");
                            int newStock = scanner.nextInt();
                            admin.updateProductStock(product, newStock);
                            product.setStockQuantity(newStock); // 更新商品的库存数量
                            saveProductsToFile(products, "products.txt"); // 保存商品信息到文件
                        } else {
                            System.out.println("Product not found.");
                        }
                    } else {
                        System.out.println("Access denied. You are not an admin.");
                    }
                    break;
                case 6:
                    viewProducts(products);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0); // 退出程序
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // 辅助方法，根据商品ID查找商品
    private static Product getProductById(List<Product> products, int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    // 辅助方法，查看商品列表
    private static void viewProducts(List<Product> products) {
        System.out.println("Product List:");
        for (Product product : products) {
            System.out.println("ID: " + product.getProductId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Stock Quantity: " + product.getStockQuantity());
            System.out.println("---------------------------");
        }
    }

    // 辅助方法，从文件加载商品信息
    private static List<Product> loadProductsFromFile(String fileName) {
        List<Product> products = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int productId = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    int stockQuantity = Integer.parseInt(parts[3]);
                    Product product = new Product(productId, name, price, stockQuantity);
                    products.add(product);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    // 辅助方法，保存商品信息到文件
    private static void saveProductsToFile(List<Product> products, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : products) {
                writer.write(product.getProductId() + "," + product.getName() + "," +
                        product.getPrice() + "," + product.getStockQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 辅助方法，从文件加载用户信息
    private static List<Customer> loadCustomersFromFile(String fileName) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int userId = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];
                    String password = parts[3];
                    Customer customer = new Customer(userId, name, email, password);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return customers;
    }

}
