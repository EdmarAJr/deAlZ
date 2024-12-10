
// import java.util.HashMap;
import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private static int idIncrement = 1;
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;

    private static final ArrayList<Product> products = new ArrayList<>();
    
    //apagar depois dos testes
    static {
        new Product("brown rice", "healthy brown rice", 5.00, 500, "food");
        new Product("bean", "nutritious beans", 15.00, 500, "food");
    }
    
    public Product(String name, String description, double price, int stock, String category) {
        if (name.isEmpty() || description.isEmpty() || price <= 0 || stock <= 0 || category.isEmpty()) {
            System.out.println("Name, description, stock, price, and category cannot be empty fields.");
            return;
        }

        if (isProductRegistered(name)) {
            System.out.println("Product already registered!");
            return;
        }

        this.id = idIncrement++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
        products.add(this);
    }
    
    private static boolean isProductRegistered(String name) {
        return products.stream().anyMatch(product -> product.name.equalsIgnoreCase(name));
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }
    
    // Buscar produto pelo ID
    public static Product findProductById(int productId) {
        for (Product product : products) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null; // Retorna null se o produto não for encontrado
    }
    
    
    // // HashMap
    // public Product(String name, String description, double price, int stock, String category) {
    //     if (name.isEmpty() || description.isEmpty() || price <= 0 || stock <= 0 || category.isEmpty()) {
    //         System.out.println("Name, description, stock, price, and category cannot be empty fields.");
    //         return;
    //     }

    //     if(productsMap.putIfAbsent(name, this) == null){
    //         this.id = idIncrement++;
    //         this.name = name;
    //         this.description = description;
    //         this.price = price;
    //         this.stock = stock;
    //         this.category = category;
    //     } else {
    //         System.out.println("product already registered!");
    //     }
    // }
    
    // public static HashMap<String, Product> getProductsMap() {
    //     return productsMap;
    // }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return this.name;
    }


    public double getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }
    
    public int decreaseStock(int quantity) {
        if(this.stock>0){
            this.stock -= quantity;
            return this.stock;
        }
        System.out.println("Mensagem de Product: Invalid quantity"); //talvez não seja preciso
        return -1;
    }
    
    public void display() {
        System.out.println("id: " + this.id);
        System.out.println("name: " + this.name);
        System.out.println("description: " + this.description);
        System.out.println("price: " + this.price);
        System.out.println("stock: " + this.stock);
        System.out.println("category: " + this.category);
        System.out.println();
    }

    //essa função não deve ser aqui
    public void displayForCart() {
        System.out.println("id: " + this.id);
        System.out.println("name: " + this.name);
        System.out.println("description: " + this.description);
        System.out.println("price: " + this.price);
        // System.out.println();
    }

}
