package br.edu.ifba.inf008.shell.screen;

import br.edu.ifba.inf008.model.Customer;
import br.edu.ifba.inf008.model.Order;
import br.edu.ifba.inf008.model.Product;
import br.edu.ifba.inf008.model.ShoppingCart;
import br.edu.ifba.inf008.model.User;

import java.util.Locale;
import java.util.Scanner;

public class CustomerMenu {
    private Customer customer;
    public CustomerMenu(Customer customer) {
        this.customer = customer;
        new ShoppingCart(customer);
    }
    private int getOption(Scanner keyboard) {
        int option = 0;
        if (keyboard.hasNextInt()) {
            option = keyboard.nextInt();
            keyboard.nextLine();
        } else {
            System.out.println("    \n****INVALID INPUT. PLEASE ENTER A VALID NUMBER.****");
            keyboard.next();
        }
        return option;
    }

    public void showMenu() {
        Scanner keyboard = new Scanner(System.in);
        keyboard.useLocale(Locale.US);
        boolean exit = false;

        while (!exit) {
            System.out.println();
            System.out.println("    |=============================|");
            System.out.println("    |       CUSTOMER MENU         |");
            System.out.println("    |   1 - view products         |");
            System.out.println("    |   2 - add cart              |");
            System.out.println("    |   3 - remove item from cart |");
            System.out.println("    |   4 - view shopping cart    |");
            System.out.println("    |   5 - finish order          |");
            System.out.println("    |   6 - view order history    |");
            System.out.println("    |   7 - logout                |");
            System.out.println("    |=============================|");
            System.out.print("    Enter your choice: ");

            int option = getOption(keyboard);

            switch (option) {
                case 1:
                    System.out.println("    \nAvailable Products:");
                    Product.display();
                    break;
                case 2:
                    boolean stop = false;
                    while (!stop) {
                        System.out.println();
                        System.out.println("    |=======================Add products=======================|");
                        System.out.println("    |                 1 - add product to cart                  |");
                        System.out.println("    |                 2 - exit                                 |");
                        System.out.println("    |==========================================================|");
                        System.out.print("    Enter your choice: ");

                        int productChoice = getOption(keyboard);

                        switch (productChoice) {
                            case 1:
                                System.out.print("    Enter the product ID and quantity: ");
                                int id = keyboard.nextInt();
                                int quantity = keyboard.nextInt();
                                keyboard.nextLine();

                                customer.getShoppingCart().addProduct(id, quantity);
                                break;
                            case 2:
                                stop = true;
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("    \n|=========================Remove products=========================|");
                    if (customer.getShoppingCart().isEmpty()) {
                        System.out.println("    \nyour shopping cart is empty.");
                    } else{
                        System.out.print("    \nEnter the product ID to remove: ");
                        int productId = keyboard.nextInt();
                        keyboard.nextLine();
                        boolean removed = customer.getShoppingCart().removeProduct(productId);
                        if (removed) {
                            System.out.println("    \nProduct with ID " + productId + " removed to the cart.");
                        } else {
                            System.out.println("    \nProduct with ID " + productId + " not found in cart.");
                        }
                    }
                    break;
                case 4:
                    System.out.println("    \n|======================Your Shopping Cart=======================|");
                    customer.displayShoppingCart();
                    break;
                case 5:
                    System.out.println("    |===========================Checkout==============================|");
                    if (customer.getShoppingCart().isEmpty()) {
                        System.out.println("    \nYour cart is empty. Add products to proceed.");
                    } else {
                        Order order = customer.getShoppingCart().checkout();
                        customer.addOrderToHistory(order);
                        System.out.println("    \nOrder placed successfully:");
                    }
                    break;
                case 6:
                    System.out.println("    |==========================Order History==========================|");
                    customer.orderHistory();
                    break;
                case 7:
                    exit = true;
                    System.out.println("    \nLogout successful.");
                    break;
                default:
                    System.out.println("    \n****INVALID OPTION. TRY AGAIN.****");
            }
        }
    }
}



