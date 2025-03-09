package br.edu.ifba.inf008.model;

import br.edu.ifba.inf008.model.Customer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.io.Serializable;
import java.text.SimpleDateFormat;

public class Order implements Serializable {
    private static int orderIncrement = 1;
    private int orderNumber;
    private final String date;
    private Customer customer;
    private Map<Product, Integer> shoppingCart;
    private double total;

    public Order(Customer customer, Map<Product, Integer> shoppingCart) {
        if (customer == null || shoppingCart == null) {
            throw new IllegalArgumentException("    CUSTOMER AND SHOPPINGCART CANNOT BE NULL\n.");
        }

        this.customer = customer;
        this.orderNumber = orderIncrement++;
        this.date = formattedDate(new Date());
        this.shoppingCart = new HashMap<>(shoppingCart); // Melhor caminho??
        this.total = calculateTotal(shoppingCart);
    }

    private double calculateTotal(Map<Product, Integer> shoppingCart) {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    private String formattedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        return sdf.format(date);
    }

    public double getTotal() {
        return this.total;
    }

    public void display() {
        System.out.println("    Order number: " + orderNumber);
        System.out.println("    Date: " + date);
        customer.display();
        System.out.println("    Products:");
        if (shoppingCart.isEmpty()) {
            System.out.println("    No products in this order.");
        } else {
            for (Map.Entry<Product, Integer> entry : shoppingCart.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                int total = entry.getValue();

                System.out.println("        Id: " + product.getId()
                        + " | Product: " + product.getName()
                        + " | Quantity: " + quantity
                        + " | Unit price: $" + product.getPrice());
            }
        }
        System.out.printf("    Total: $%.2f%n", total);
    }
}
