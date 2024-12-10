import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class AdminMenu {
    private Administrator admin;

    public AdminMenu(Administrator admin) {
        this.admin = admin;
    }

    public void showMenu() {
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("    |=======================================|");
            System.out.println("    |         ADMINISTRATOR MENU            |");
            System.out.println("    | 1 - create new product                |");
            System.out.println("    | 2 - create new user                   |");
            System.out.println("    | 3 - display products                  |");
            System.out.println("    | 4 - display orders                    |");
            System.out.println("    | 5 - report: largest order             |");
            System.out.println("    | 6 - report: product with lowest stock |");
            System.out.println("    | 7 - logout                            |");
            System.out.println("    |=======================================|");
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
                    createProduct(keyboard);
                    break;
                case 2:
                    createUser(keyboard);
                    break;
                case 3:
                    admin.displayProducts();
                    break;
                case 4:
                    admin.displayOrders();
                    break;
                case 5:
                    admin.displayLargestOrder();
                    break;
                case 6:
                    admin.displayLowestStockProduct();
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
    
    private void createProduct(Scanner keyboard) {
        System.out.print("enter product name: ");
        String name = keyboard.nextLine();
                    
        System.out.print("enter product description: ");
        String description = keyboard.nextLine();
                    
        System.out.print("enter product price: ");
        double price = keyboard.nextDouble();
                    
        System.out.print("enter product stock: ");
        int stock = keyboard.nextInt();
        keyboard.nextLine(); 
                    
        System.out.print("enter product category: ");
        String category = keyboard.nextLine();
                    
        //admin.addProduct(name, description, price, stock, category);
        new Product(name, description, price, stock, category);

     }
     
    private void createUser(Scanner keyboard) {
        System.out.print("enter name: ");
        String userName = keyboard.nextLine();
        
        System.out.print("enter email: ");
        String email = keyboard.nextLine();
            
        System.out.print("enter password: ");
        String password = keyboard.nextLine();
        
        if(email.endsWith("@dealz.com")){
            new Administrator(userName, email, password);
        } else{
            System.out.print("enter adress: ");
            String adress = keyboard.nextLine();
            new Customer(userName, email, password, adress);
        }
    }
     
    public void displayOrders() {
        System.out.println("\nDisplaying all orders:");

        HashMap<Integer, Order> orders = Order.getAllOrders();
        
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders.values()) {
                order.display();
                System.out.println("------------------------");
            }
        }
    }
}
