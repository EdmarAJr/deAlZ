package inf008.ecomerce.dealz;

import java.io.Serializable;

public class Product implements Serializable {
    private static int idIncrement = 1;
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String category;

    public Product(String name, String description, double price, int stock, String category) {
        if (name.isEmpty() || description.isEmpty() || price <= 0 || stock <= 0 || category.isEmpty()) {
            throw new IllegalArgumentException("    NAME, DESCRIPTION, STOCK, PRICE, AND CATEGORY CANNOT BE EMPTY FIELDS.\n.");
        }
        this.id = idIncrement++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public static void addProduct(Product product) {
        if (isProductRegistered(product.name)) {
            throw new IllegalArgumentException("    Product " + product.getName() + "already registered.");
        } else {
            FileRegister.products.add(product);
            System.out.println("    Product added successfully: " + product.getName()); //retirar depois
        }
    }

    public static void display() {
        if (FileRegister.products.isEmpty()) {
            throw new IllegalArgumentException("    .NO PRODUCTS AVAILABLE");
        } else {
            for (Product product : FileRegister.products) {
                System.out.println("    id: " + product.id);
                System.out.println("    name: " + product.name);
                System.out.println("    description: " + product.description);
                System.out.println("    price: " + product.price);
                System.out.println("    stock: " + product.stock);
                System.out.println("    category: " + product.category);
                System.out.println();
            }
        }
    }

    public void printDetails() {
        System.out.println("Product ID: " + this.getId());
        System.out.println("Name: " + this.getName());
        System.out.println("Stock: " + this.getStock());
        System.out.println("Price: $" + this.getPrice());
    }

    public static boolean isProductRegistered(String name) {
        return FileRegister.products.stream().anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }

    public static Product findProductById(int productId) {
        for (Product product : FileRegister.products) {
            if (product.id == productId) {
                return product;
            }
        }
        return null;
    }

    public static void decreaseStock(int productId, int quantity) {
        Product product = findProductById(productId);
        if (product == null) throw new IllegalArgumentException("    PRODUCT NOT FOUND.");

        if(quantity <= product.stock) {
            product.stock -= quantity;
        } else {
            throw new IllegalArgumentException("    QUANTITY TOO HIGH.");
        }
    }

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
        return stock;
    }


    public static void saveFile() throws Exception {
        for (Product product : FileRegister.products) {
            FileRegister.addToFile(product);
        }
    }

//    public void display() {
//        System.out.println("    id: " + this.id);
//        System.out.println("    name: " + this.name);
//        System.out.println("    description: " + this.description);
//        System.out.println("    price: " + this.price);
//        System.out.println("    stock: " + this.stock);
//        System.out.println("    category: " + this.category);
//        System.out.println();
//    }
//
}
