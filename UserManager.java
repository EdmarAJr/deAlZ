import java.util.ArrayList;
import java.util.Objects;

public class UserManager {
    // private ArrayList<User> users;
     // Lista compartilhada de usuários
    // private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Product> productList = new ArrayList<>();
    
    
    // public UserManager() {
    //     if (users.isEmpty()) {
    //         users.add(new Administrator("admin", "admin@email.com", "admin"));
    //     }
    // }
    
    // public static boolean addUser(User user) {
    //     if (emailExists(user.getEmail())) {
    //         System.out.println("email already registered");
    //         return false;
    //     }
    //     users.add(user);
    //     return true;
    // }


    // public static boolean emailExists(String email) {
    //     return users.stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    // }
    
    // public static User authenticate(String email, String password) {
    //     for (User user : users) {
    //         if (user.getEmail().equalsIgnoreCase(email) && user.validatePassword(password)) {
    //             return user;
    //         }
    //     }
    //     return null;
    // }
    
    public static ArrayList<Product> getProductList() {
        return productList;
    }

    public static void addProduct(Product product) {
        productList.add(product);
    }
}
