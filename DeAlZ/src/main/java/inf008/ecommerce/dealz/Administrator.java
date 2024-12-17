package inf008.ecomerce.dealz;

import java.util.Comparator;

import static inf008.ecomerce.dealz.FileRegister.orders;
import static inf008.ecomerce.dealz.FileRegister.products;

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

    public static void addOrder (Order order) {
        orders.add(order);
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

    // Relatório: Produto com menor estoque
//    public void displayLowestStockProduct() {
//
//
//        Product lowestStockProduct = products.stream().min((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()))
//                .orElse(null);
//
//        if (lowestStockProduct != null) {
//            System.out.println();
//            System.out.println("    Product with Lowest Stock:");
//            lowestStockProduct.display();
//            System.out.println("Stock: " + lowestStockProduct.getStock());
//        }
//    }


    @Override
    public void showMenu() {
        new AdministratorMenu(this).showMenu();
    }
}

