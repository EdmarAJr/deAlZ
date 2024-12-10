import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private static int orderIncrement = 1;
    private int orderNumber;
    private final String date;
    private Customer customer;
    private double total;
    
    private static final HashMap<Integer, Order> ordersMap = new HashMap<>();
    private final HashMap<Product, Integer> products;
    
    public Order(Customer customer) {
        this.orderNumber = orderIncrement++;
        this.date = formattedDate(new Date());
        this.customer = customer;
        this.products = new HashMap<>();
        this.total = 0;
    }
    
    //
    public void addProduct(Product product, int quantity) {
        if (product.getStock() >= quantity) {
            products.put(product, quantity);
            product.decreaseStock(quantity); //decrementar o stock
            total += product.getPrice() * quantity;
        } else {
            System.out.println("insufficient stock for product: " + product.getName());
        }
    }
    
    private String formattedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        return sdf.format(date);
    }

    public static void addOrderToMap(Order order) {
        ordersMap.put(order.orderNumber, order);
    }
    
    public static HashMap<Integer, Order> getAllOrders() {
        return ordersMap;
    }
   
    //verificar a necessidade desse método
    // public double getTotal() {
    //     return total;
    // }
    
    
    //verificar a necessidade de calcular o total em order e não no carrinho
    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    //método para consumidador
    public void display() {
        System.out.println("order number: " + this.orderNumber);
        System.out.println("date: " + this.date);
        this.customer.display();
        System.out.println("products: ");
        products.forEach((product, quantity) -> {
            System.out.println("- " + product.getName() + " (x" + quantity + ")");
        });
        System.out.println("total: $" + this.total);
        System.out.println();
    }
    
    //método para administrador
    private void displayOrders() {
        HashMap<Integer, Order> orders = Order.getAllOrders();
        if (orders.isEmpty()) {
            System.out.println("\nNo orders found.");
        } else {
            for (Order order : orders.values()) {
                order.display();
                System.out.println("------------------------");
            }
        }
    }
}
