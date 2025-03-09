package br.edu.ifba.inf008.model;

import static br.edu.ifba.inf008.data.FileRegister.products;
import static br.edu.ifba.inf008.data.FileRegister.orders;
import br.edu.ifba.inf008.interfaces.IMenu;
import br.edu.ifba.inf008.shell.screen.AdministratorMenu;

import java.util.Comparator;
import br.edu.ifba.inf008.model.Order;
import br.edu.ifba.inf008.model.User;
import br.edu.ifba.inf008.model.Product;


public class Administrator extends User implements IMenu {
    public Administrator(String name, String email, String password) {
        super(name, email, password);
    }

    public void displayOrders() {
        //pensar num método para retornar os erros direto do menu se não der certo, voltar com os println
        if (orders.isEmpty()) System.out.println("    NO ORDERS AVAILABLE.");
        System.out.println();
        System.out.println("    Orders:");
        for (Order order : orders) {
            order.display();
            System.out.println("-----------------------------------------");
        }
    }

    public void displayLargestOrder() {
        if (orders.isEmpty()) System.out.println("    NO ORDERS AVAILABLE");

        Order largestOrder = null;
        double maxTotal = 0;

        for (Order order : orders) {
            double orderTotal = order.getTotal();
            if (orderTotal > maxTotal) {
                maxTotal = orderTotal;
                largestOrder = order;
            }
        }

        if (largestOrder != null) {
            System.out.println();
            System.out.println("    Largest Order:");
            largestOrder.display();
        }
    }

    @SuppressWarnings("static-access")
    public void displayLowestStockProduct() {
        if (products.isEmpty()) {
            System.out.println("    NO PRODUCTS AVAILABLE.");
            return;
        }

        Product lowestStockProduct = products.stream()
                .min(Comparator.comparingInt(Product::getStock))
                .orElse(null);

        System.out.println();
        System.out.println("    Product with Lowest Stock:");

        if (lowestStockProduct != null) lowestStockProduct.printDetails();
    }

    @Override
    public void showMenu() {
        new AdministratorMenu(this).showMenu();
    }
}

