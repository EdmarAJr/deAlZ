package br.edu.ifba.inf008.data;

import br.edu.ifba.inf008.model.Administrator;
import br.edu.ifba.inf008.model.Customer;
import br.edu.ifba.inf008.model.Order;
import br.edu.ifba.inf008.model.Product;
import br.edu.ifba.inf008.model.ShoppingCart;
import br.edu.ifba.inf008.model.User;

import java.io.IOException;
import java.io.File;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

public abstract class FileRegister {

    public static ArrayList<Product> products = new ArrayList<>();
    public static Map<String, User> users = new HashMap<>();
    public static List<Order> orders = new ArrayList<>();
    public static List<ShoppingCart> shoppingCart = new ArrayList<>();

    static {
        addUser( new Administrator("Admin", "admin@dealz.com", "admin"));
        addUser( new Customer("Cliente 1", "cliente@email.com", "1234", "Rua dos bobos"));
    }

    static {
        addProduct(new Product("brown rice", "healthy brown rice", 5.00, 500, "food"));
        addProduct(new Product("bean", "nutritious beans", 15.00, 500, "food"));
    }

    private static FileInputStream fis;
    private static ObjectInputStream ois;
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static final String FILE_PATH = "dealzRegister.dat";


    public static void loadFile() throws IOException, ClassNotFoundException  {
        System.out.println("LOADING DATA...");
        File file = new File(FILE_PATH);

        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (parentDirectory.mkdirs()) {
                System.out.println("     DIRECTORIES CREATE: " + parentDirectory.getAbsolutePath());
            } else {
                throw new IOException("    UNABLE TO CREATE DIRECTORIES: " + parentDirectory.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            System.out.println("    FILE " + FILE_PATH + " NOT FOUND. CREATING NEW FILE.");
            return;
        }

        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

            Object obj;
            try {
                while (true) {
                    obj = ois.readObject();

                    if (obj instanceof User) {
                        FileRegister.users.put(((User) obj).getEmail(), (User) obj);
                    } else if (obj instanceof Product) {
                        FileRegister.products.add((Product) obj);
                    } else if (obj instanceof Order) {
                        FileRegister.orders.add((Order) obj);
                    } else if (obj instanceof ShoppingCart) {
                        FileRegister.shoppingCart.add((ShoppingCart) obj);
                    }
                }
            } catch (EOFException ex) {}
        } finally {
            if (fis != null) fis.close();
            if (ois != null) ois.close();
        }
    }

    public static void saveFile() throws Exception {
        File file = new File(FILE_PATH);
        File parentDirectory = file.getParentFile();
        if (parentDirectory != null && !parentDirectory.exists()) {
            if (parentDirectory.mkdirs()) {
                System.out.println("     DIRECTORIES CREATE: " + parentDirectory.getAbsolutePath());
            } else {
                throw new IOException("   UNABLE TO CREATE DIRECTORIES: " + parentDirectory.getAbsolutePath());
            }
        }

        try {
            fos = new FileOutputStream(FILE_PATH, false);
            oos = new ObjectOutputStream(fos);

            for (User u : users.values()) {
                oos.writeObject(u);
            }
            for (Product p : products) {
                oos.writeObject(p);
            }
            for (Order o : orders) {
                oos.writeObject(o);
            }
            System.out.println("    DATA SAVED SUCCESSFULLY!");
        } finally {
            if (fos != null) fos.close();
            if (oos != null) oos.close();
        }
    }

    public static void addUser(User user) {
        if (user.getEmail() != null) {
            users.put(user.getEmail(), user);
            user.display();
        } else {
            System.out.println("    ERROR ADDING USER");
        }
    }


    public static void addProduct(Product product) {
        if (products.contains(product)) {
            throw new IllegalArgumentException("    Product " + product.getName() + "already registered.");
        } else {
            products.add(product);
            System.out.println("    Product added successfully: " + product.getName()); //retirar depois
        }
    }

    public static void addOrder(Order order) {
        orders.add(order);
        System.out.println("    Order added successfully: " + order.getTotal()); //retirar depois
    }
}