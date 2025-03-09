package br.edu.ifba.inf008.model;

import br.edu.ifba.inf008.interfaces.IMenu;
import br.edu.ifba.inf008.model.Order;
import br.edu.ifba.inf008.model.ShoppingCart;
import br.edu.ifba.inf008.model.User;
import br.edu.ifba.inf008.shell.screen.CustomerMenu;

import java.util.ArrayList;

public class Customer extends User implements IMenu {
    private String deliveryAddress;
    private ShoppingCart shoppingCart;
    private ArrayList<Order> orderHistory;

    public Customer(String name, String email, String password, String deliveryAddress) {
        super(name, email, password);
        this.deliveryAddress = deliveryAddress;
        this.shoppingCart = new ShoppingCart(this);
        this.orderHistory = new ArrayList<>();
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void displayShoppingCart() {
        shoppingCart.displayCart();
    }

    public void orderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("    \nNo orders found.");
        } else {
            for (Order order : orderHistory) {
                order.display();
                System.out.println("    -----------------------------------------------");
            }
        }
    }

    @Override
    public void display() {
        super.display();
        System.out.println("    Address: " + this.deliveryAddress);
    }

    public void addOrderToHistory(Order order) {
        orderHistory.add(order);
    }

    @Override
    public void showMenu() {
        new CustomerMenu(this).showMenu();
    }
}
