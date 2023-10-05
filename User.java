import java.util.*;
class User {
    private int userId;
    private String name;
    private String email;
    private String password;

    // Constructor
    public User(int userId, String name, String email,String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Common methods applicable to all users
    public void login() {
        System.out.println(name + " logged in.");
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}