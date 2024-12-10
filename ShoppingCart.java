import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private final HashMap<Product, Integer> cartMap;
    private Customer customer; // Cliente associado ao carrinho

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.cartMap = new HashMap<>();
    }

    // Adicionar produto ao carrinho usando ID ao carrinho do cliente(é local??)
    public void addProduct(int productId, int quantity) {
        Product product = Product.findProductById(productId); // Busca produto pelo ID
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

        cartMap.put(product, cartMap.getOrDefault(product, 0) + quantity);
        //product.decreaseStock(quantity); // Reduz o estoque do produto. Validar se ao retirar o tamanho retorna
        System.out.println("Added " + quantity + " of " + product.getName() + " to the cart.");
    }

        // public ShoppingCart(Customer customer, Product product) {
    //     this.idCustomer = customer.id;
    //     this.name = customer.name;
        
    //     if (cart.containsKey(product)) {
    //         cartMap.put(product, cart.get(product) + quantity);
    //     } else {
    //         cartMap.put(product, quantity);
    //     }
    // }
    
    // 
    // public void removeProduct(Product product) {
    //     if (cartMap.containsKey(product)) {
    //         cartMap.remove(product);
    //     } else {
    //         System.out.println("empty cart.");
    //     }
    // }
    
    
    public boolean isEmpty() {
        return cartMap.isEmpty();
    }
    
    // Remover produto do carrinho usando ID
    public boolean removeProduct(int productId) {
     Product product = Product.findProductById(productId); // Busca produto pelo ID
        
        if (product == null || !cartMap.containsKey(product)) {
            return false;
        }
         
        cartMap.remove(product);
        return true;
    }
    
    public boolean removeProduct(int productId, int quantity) {
        Product product = Product.findProductById(productId); // Busca produto pelo ID
        
        if (product == null || !cartMap.containsKey(product)) {
            return false;
        }

        int currentQuantity = cartMap.get(product);
        if (quantity >= currentQuantity) {
            cartMap.remove(product); // Remove o produto se a quantidade a ser removida é igual ou maior que a quantidade atual
        } else {
            cartMap.put(product, currentQuantity - quantity); // Atualiza a quantidade do produto
        }
        return true;
    }

    public void displayCart() {
        if (cartMap.isEmpty()) {
            System.out.println("\nyour shopping cart is empty.");
        } else {
            for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
                Product product = entry.getKey();
                int quantity = entry.getValue();
                product.displayForCart();
                System.out.println("quantity: " + quantity + "\n");
            }
            System.out.println("total: $" + calculateTotal());
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            total += product.getPrice() * quantity;
        }
        return total;
    }

    public Order checkout() {
        Order order = new Order(this.customer);
        for (Map.Entry<Product, Integer> entry : cartMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            //product.decreaseStock(quantity); // Reduz o estoque do produto.
            order.addProduct(product, quantity);
        }
        customer.addOrderToHistory(order);//está funcionando????
        cartMap.clear();  // Limpar o carrinho após o checkout
        
            // Adicionar o pedido ao mapa global
        Order.addOrderToMap(order);
        return order;
    }
}
