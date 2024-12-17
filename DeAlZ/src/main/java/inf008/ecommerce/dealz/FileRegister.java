package inf008.ecomerce.dealz;
import static inf008.ecomerce.dealz.Product.addProduct;

import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.ObjectOutputStream;

public class FileRegister {

    public static final ArrayList<Product> products = new ArrayList<>();
    public static final HashMap<String, User> users = new HashMap<>();
    public static ArrayList<Order> orders = new ArrayList<>();
    public static ArrayList<ShoppingCart> shoppingCart = new ArrayList<>();

    static {
        new Administrator("Admin", "admin@dealz.com", "admin");
    }

    // apagar depois dos testes
//    static {
//        new Customer("Cliente 1", "cliente@email.com", "1234", "Rua dos bobos");
//    }
//
//    static {
//        products.add(new Product("brown rice", "healthy brown rice", 5.00, 500, "food"));
//        products.add(new Product("bean", "nutritious beans", 15.00, 500, "food"));
//    }

    private static FileInputStream fis;
    private static ObjectInputStream ois;
    private static FileOutputStream fos;
    private static ObjectOutputStream oos;
    private static final String FILE_PATH = "dealzRegister.dat";

    public FileRegister() throws Exception {
    }

    public static void loadFile() throws Exception {
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

            oos.writeObject("(START)");

            for (User u : users.values()) {
                oos.writeObject(u);
            }
            for (Product p : products) {
                oos.writeObject(p);
            }
            for (Order o : orders) {
                oos.writeObject(o);
            }

            oos.writeObject("(FINISH)");
            System.out.println("    DATA SAVED SUCCESSFULLY!");
        } finally {
            if (fos != null) fos.close();
            if (oos != null) oos.close();
        }
    }

    public static void addToFile(Object obj) throws Exception {
        File file = new File(FILE_PATH);

        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            ObjectOutputStream oos;

            if (file.length() == 0) {
                oos = new ObjectOutputStream(fos);
            } else {
                oos = new CustomObjectOutputStream(fos);
            }

            oos.writeObject(obj);
            oos.close();
        }
    }

    private static class CustomObjectOutputStream extends ObjectOutputStream {
        public CustomObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
        }
    }
}