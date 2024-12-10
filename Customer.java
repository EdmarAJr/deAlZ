import java.util.ArrayList;

public class Customer extends User {
    private String deliveryAddress;
    //private ShoppingCart cart;
    private Order order;
    private ArrayList<Order> orderHistory;

    public Customer(String name, String email, String password, String deliveryAddress) {
        super(name, email, password);
        this.deliveryAddress = deliveryAddress;
        //this.cart = new ShoppingCart(this);
        this.orderHistory = new ArrayList<>();
    }
    
    public void displayProducts() {
        for (Product product : Product.getProducts()) {
            product.display();
        }
    }
    
    //adiciona produto e quantidade ao carrinho do consumidor
    // public void addProductToCart(int productId, int quantity) {
    //     cart.addProduct(productId, quantity);
    // }
    
    //remover produto completo
    // public void removeProductToCart(int productId) {
    //     cart.removeProduct(productId);
    // }
    
    // //diminuir  quantidade
    // public void removeProductToCart(int productId, int quantity) {
    //     cart.removeProduct(productId, quantity);
    // }
    

    // Exibir histórico de compras
    // public void displayOrderHistory() {
    //     if (orderHistory.isEmpty()) {
    //         System.out.println("No orders found in your history.");
    //     } else {
    //         System.out.println("Order History:");
    //         for (Order order : orderHistory) {
    //             order.display();
    //         }
    //     }
    // }
    
    public void orderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("\nNo orders found.");
        } else {
            for (Order order : orderHistory) {
                order.display(); // Método para exibir os detalhes do pedido
                System.out.println("------------------------");
            }
        }
    }
    
    // public ShoppingCart getShoppingCart() {
    //     return this.cart;
    // }
    
    public void addOrderToHistory(Order order) {
        orderHistory.add(order); // Adiciona pedido ao histórico
    }
    
    // public void displayProducts() {
    //     for (Product product : Product.getProductsMap().values()) {
    //         product.display();
    //     }
    // }

    // @Override
    // public void display() {
    //     System.out.println("ID: " + this.id);
    //     System.out.println("Name: " + this.name);
    //     System.out.println("Address: " + this.deliveryAddress);
    // }


    
    // public Order checkout(ShoppingCart cart) {
    //     return new Order(this, cart.getProducts());
    // }
    
    // public Order getOrder() {
    //     return order;
    // }
}

