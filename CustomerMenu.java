import java.util.ArrayList;
import java.util.Scanner;

public class CustomerMenu {
    private Customer customer;
    private ShoppingCart customerCart;
    public CustomerMenu(Customer customer) {
        this.customer = customer;
        this.customerCart = new ShoppingCart(customer); //ver se isso vai dar certo
    }

    public void showMenu() {
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            System.out.println("    |==========================|");
            System.out.println("    |     CUSTOMER MENU        |");
            System.out.println("    |   1 - view products      |");
            System.out.println("    |   2 - add cart           |");
            System.out.println("    |   3 - remove cart        |");
            System.out.println("    |   4 - view cart          |");
            System.out.println("    |   5 - checkout           |");
            System.out.println("    |   6 - view order history |"); 
            System.out.println("    |   7 - logout             |");
            System.out.println("    |==========================|");
            System.out.print("    Enter your choice: ");

            int option = 0;

            if(keyboard.hasNextInt()) {
                option = keyboard.nextInt();
                keyboard.nextLine(); // Consumir a nova linha
            }else{
                System.out.println("\nInvalid input. Please enter a valid number.");
                keyboard.next(); // Consome a entrada inválida
                continue;
            }
            
            switch (option) {
                case 1:
                    //viewProducts();
                    System.out.println("\nAvailable Products:");
                    customer.displayProducts(); // Exibe os produtos disponíveis
                    break;
                case 2:
                    //addProductToCart(keyboard);
                    System.out.print("Add products - enter the product ID and quantity: ");
                    int id = keyboard.nextInt();
                    int quantity = keyboard.nextInt();
                    keyboard.nextLine(); // Consumir a nova linha
            
                    //customer.addProductToCart(id, quantity);
                    customerCart.addProduct(id, quantity);
                    break;
                case 3:
                    //removeProductFromCart(keyboard);//não está funcionando
                    //removeProductToCart(keyboard); //não está funcionando
                    if (customerCart.isEmpty()) {
                        System.out.println("\nyour shopping cart is empty.");
                    } else{
                        System.out.print("\nRemove products - enter the product ID to remove: ");
                        int productId = keyboard.nextInt();
                        keyboard.nextLine();
                        boolean removed = customerCart.removeProduct(productId);
                        if (removed) {
                            System.out.println("\nProduct with ID " + productId + " removed to the cart.");
                        } else {
                            System.out.println("\nProduct with ID " + productId + " not found in cart.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nYour Shopping Cart:");
                    //viewCart();
                    customerCart.displayCart();
                    break;
                case 5:
                    //checkout();
                    System.out.println("\nCheckout:");
                    if (customerCart.isEmpty()) {
                        System.out.println("\nYour cart is empty. Add products to proceed.");
                    } else {
                        customerCart.checkout();
                        System.out.println("\nOrder placed successfully:");
                        //order.display();
                    }
                    break;
                case 6:
                    //viewOrderHistory();
                    System.out.println("\nOrder History:");
                    customer.orderHistory();
                    //ArrayList<Order> orderHistory = customer.getOrderHistory(); // Obtemos o histórico do cliente
        
                    
                    break;
                case 7:
                    exit = true;
                    System.out.println("\nLogout successful.");
                    break;
                default:
                    System.out.println("\nInvalid option. Try again.");
            }
        }
    }
    
    // private void viewProducts() {
    //     System.out.println("\nAvailable Products:");
    //     customer.displayProducts(); // Exibe os produtos disponíveis
    // }
    
    // private void addProductToCart(Scanner keyboard) {
    //     System.out.print("Add products - enter the product ID and quantity: ");
    //     int id = keyboard.nextInt();
    //     int quantity = keyboard.nextInt();
    //     keyboard.nextLine(); // Consumir a nova linha

    //     //customer.addProductToCart(id, quantity);
    //     cart.addProduct(id, quantity);
    // }
    
    // private void removeProductFromCart(Scanner keyboard) {
    //     System.out.print("\nRemove products - enter the product ID to remove: ");
    //     int id = keyboard.nextInt();
    //     keyboard.nextLine();

    //     if (customer.getShoppingCart().isEmpty()) {
    //         System.out.println("\nyour shopping cart is empty.");
    //     } else{
    //         boolean removed = customer.getShoppingCart().removeProduct(id);
            
    //         customer.removeProductToCart();
    //         //System.out.println(removed);
    //         if (removed) {
    //             System.out.println("\nProduct with ID " + id + " removed to the cart.");
    //         } else {
    //             System.out.println("\nProduct with ID " + id + " not found in cart.");
    //         }
    //     }
    // }
    
    // private void viewCart() {
    //     System.out.println("\nYour Shopping Cart:");
    //     customer.getShoppingCart().displayCart();
    // }
    
    // private void checkout() {
    //     System.out.println("\nCheckout:");
    //     if (customer.getShoppingCart().isEmpty()) {
    //         System.out.println("\nYour cart is empty. Add products to proceed.");
    //     } else {
    //         // Realiza o checkout e cria o pedido
    //         Order order = customer.getShoppingCart().checkout();
    
    //         // Adiciona o pedido ao histórico do cliente
    //         customer.addOrderToHistory(order);
    
    //         // Confirmação de sucesso
    //         System.out.println("\nOrder placed successfully:");
    //         order.display();
    //     }
    // }

    // private void viewOrderHistory() {
    //     System.out.println("\nOrder History:");
    //     ArrayList<Order> orderHistory = customer.getOrderHistory(); // Obtemos o histórico do cliente
        
    //     if (orderHistory.isEmpty()) {
    //         System.out.println("\nNo orders found.");
    //     } else {
    //         for (Order order : orderHistory) {
    //             order.display(); // Método para exibir os detalhes do pedido
    //             System.out.println("------------------------");
    //         }
    //     }
    // }
}


