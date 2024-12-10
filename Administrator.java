import java.util.HashMap;
//import java.util.ArrayList;

public class Administrator extends User {
    //private ArrayList<Order> orderList;
    //private ArrayList<Order> orderList = new ArrayList<>();
    
    public Administrator(String name, String email, String password) {
        super(name, email, password);
        //this.orderList = new ArrayList<>();
    }

    public void displayProducts() {
        for (Product product : Product.getProducts()) {
            product.display();
        }
    }
    
    // // HashMap
    // public void displayProducts() {
    //     // Access productsMap via the static getter in Product class
    //     for (Product product : Product.getProductsMap().values()) {
    //         product.display();  // Call the display method of each product
    //     }
    // }

    //essa deve ser uma atribuição de order
    // public void processOrder(Order order) {
    //     orderList.add(order);
    //     System.out.println("order processed: ");
    //     order.display();
    // }

    // public void displayOrders() {
    //     if (orderList.isEmpty()) {
    //         System.out.println("No orders available.");
    //     } else {
    //         System.out.println("Orders:");
    //         for (Order order : orderList) {
    //             order.display();
    //         }
    //     }
    // }
    
    public void displayOrders() {
        HashMap<Integer, Order> orders = Order.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("Orders:");
            for (Order order : orders.values()) {
                order.display();
                System.out.println("------------------------");
            }
        }
    }
    
     // Exibe o maior pedido baseado no total
    public void displayLargestOrder() {
        HashMap<Integer, Order> orders = Order.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("No orders available to display.");
            return;
        }

        Order largestOrder = null;
        double maxTotal = 0;

        for (Order order : orders.values()) {
            double orderTotal = order.calculateTotal();
            if (orderTotal > maxTotal) {
                maxTotal = orderTotal;
                largestOrder = order;
            }
        }

        if (largestOrder != null) {
            System.out.println("Largest Order:");
            largestOrder.display();
            System.out.println("Total Value: $" + maxTotal);
        }
    }

    // Relatório: Produto com menor estoque
    public void displayLowestStockProduct() {
        if (Product.getProducts().isEmpty()) {
            System.out.println("No products available to display.");
            return;
        }

        Product lowestStockProduct = Product.getProducts().stream()
                .min((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()))
                .orElse(null);

        if (lowestStockProduct != null) {
            System.out.println("Product with Lowest Stock:");
            lowestStockProduct.display();
            System.out.println("Stock: " + lowestStockProduct.getStock());
        }
    }

    
    // public void displayLargestOrder() {
    //     if (orderList.isEmpty()) {
    //         System.out.println("No orders available to display.");
    //         return;
    //     }

    //     Order largestOrder = null;
    //     double maxTotal = 0;

    //     for (Order order : orderList) {
    //         double orderTotal = order.calculateTotal(); // Supondo que `Order` tenha um método `calculateTotal`
    //         if (orderTotal > maxTotal) {
    //             maxTotal = orderTotal;
    //             largestOrder = order;
    //         }
    //     }

    //     if (largestOrder != null) {
    //         System.out.println("Largest Order:");
    //         largestOrder.display();
    //         System.out.println("Total Value: $" + maxTotal);
    //     }
    // }

    // // Relatório: Produto com menor estoque
    // public void displayLowestStockProduct() {
    //     ArrayList<Product> products = Product.getProducts(); // Supondo que `Product.getProducts()` retorna todos os produtos

    //     if (products.isEmpty()) {
    //         System.out.println("No products available to display.");
    //         return;
    //     }

    //     //Product lowestStockProduct = null;
        
    //     Product lowestStockProduct = products.stream()
    //                                      .min((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock()))
    //                                      .orElse(null);
                                         
    //     if (lowestStockProduct != null) {
    //         System.out.println("Product with Lowest Stock:");
    //         lowestStockProduct.display();
    //         System.out.println("Stock: " + lowestStockProduct.getStock());
    //     }
    //     //int lowestStock = Integer.MAX_VALUE;

    //     // for (Product product : products) {
    //     //     if (product.getStock() < lowestStock) {
    //     //         lowestStock = product.getStock();
    //     //         lowestStockProduct = product;
    //     //     }
    //     // }

    //     // if (lowestStockProduct != null) {
    //     //     System.out.println("Product with Lowest Stock:");
    //     //     lowestStockProduct.display();
    //     //     System.out.println("Stock: " + lowestStock);
    //     // }
    // }
}
