package br.edu.ifba.inf008.model;

import br.edu.ifba.inf008.model.Customer;
import static br.edu.ifba.inf008.data.FileRegister.addOrder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class ShoppingCart implements Serializable {
    private final HashMap<Product, Integer> productCartMap;
    private Customer customer;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.productCartMap = new HashMap<>();
    }

    public Map<Product, Integer> getProductCartMap() {
        return productCartMap;
    }

    public void addProduct(int productId, int quantity) {
        Product product = Product.findProductById(productId);
        if (product == null) {
            System.out.println("Product with ID " + productId + " not found.");
            return;
        }

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        if (product.getStock() < quantity) {
            System.out.println("Insufficient stock for product: " + product.getName());
            return;
        }

        productCartMap.put(product, productCartMap.getOrDefault(product, 0) + quantity);
        System.out.println("Added " + quantity + " of " + product.getName() + " to the cart.");
    }

    public boolean isEmpty() {
        return productCartMap.isEmpty();
    }

    public boolean removeProduct(int productId) {
     Product product = Product.findProductById(productId);

        if (product == null || !productCartMap.containsKey(product)) {
            return false;
        }
        productCartMap.remove(product);
        return true;
    }

    public void displayCart() {
        if (productCartMap.isEmpty()) {
            System.out.println("    \nyour shopping cart is empty.");
        } else {
            for (Map.Entry<Product, Integer> entry : productCartMap.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                System.out.println("   Id: " + product.getId()
                                 + " | Product: " + product.getName()
                                 + " | Quantity " + quantity  + " | Unit price  $" + product.getPrice());
                System.out.println("-------------------------------------------------------------------") ;
            }
            System.out.println("total: $" + calculateTotal());
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : productCartMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    public Order checkout() {
        if (this.customer == null) {
            throw new IllegalStateException("    CUSTOMER IS NOT ASSOCIATED WITH THIS SHOPPING CART\n.");
        }

        for (Map.Entry<Product, Integer> entry : productCartMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            Product.decreaseStock(product.getId(), quantity);
        }

        Order order = new Order(customer, productCartMap);
        addOrder(order);
        productCartMap.clear();

        return order;
    }


    //    public boolean removeProduct(int productId, int quantity) {
//        Product product = Product.findProductById(productId);
//
//        if (product == null || !productCartMap.containsKey(product)) {
//            return false;
//        }
//
//        int currentQuantity = productCartMap.get(product);
//        if (quantity >= currentQuantity) {
//            productCartMap.remove(product);
//        } else {
//            productCartMap.put(product, currentQuantity - quantity);
//        }
//        return true;
//    }

}
