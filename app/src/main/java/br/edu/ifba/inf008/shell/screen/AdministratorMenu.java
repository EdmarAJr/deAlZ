package br.edu.ifba.inf008.shell.screen;

import br.edu.ifba.inf008.model.Product;
import br.edu.ifba.inf008.model.Administrator;
import br.edu.ifba.inf008.model.Customer;
import static br.edu.ifba.inf008.data.FileRegister.addProduct;
import static br.edu.ifba.inf008.data.FileRegister.addUser;

import java.util.Locale;
import java.util.Scanner;


public class AdministratorMenu {
    private Administrator admin;

    public AdministratorMenu(Administrator admin) {
        this.admin = admin;
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
            System.out.println("    |===========================================|");
            System.out.println("    |         ADMINISTRATOR MENU                |");
            System.out.println("    | 1 - create new product                    |");
            System.out.println("    | 2 - create new user                       |");
            System.out.println("    | 3 - display products                      |");
            System.out.println("    | 4 - display orders                        |");
            System.out.println("    | 5 - report: more expensive order          |");
            System.out.println("    | 6 - report: product with lowest inventory |");
            System.out.println("    | 7 - logout                                |");
            System.out.println("    |===========================================|");
            System.out.print("    Enter your choice: ");

            int option = getOption(keyboard);

            switch (option) {
                case 1:
                    boolean stop = false;
                    while (!stop) {
                        System.out.println("    |===================Create products====================|");
                        System.out.println("    |              1 - add new product                     |");
                        System.out.println("    |              2 - exit                                |");
                        System.out.println("    |======================================================|");
                        System.out.print("    Enter your choice: ");

                        int choiceProduct = getOption(keyboard);

                        switch (choiceProduct) {
                            case 1:
                                System.out.println();
                                System.out.print("   enter product name: ");
                                String productName = keyboard.nextLine();

                                System.out.print("   enter product description: ");
                                String productDescription = keyboard.nextLine();

                                System.out.print("   enter product price: ");
                                double productPrice = keyboard.nextDouble();

                                System.out.print("   enter product stock: ");
                                int productStock = keyboard.nextInt();
                                keyboard.nextLine();

                                System.out.print("   enter product category: ");
                                String productCategory = keyboard.nextLine();

                                addProduct(new Product(productName, productDescription, productPrice, productStock, productCategory));
                                break;
                            case 2:
                                stop = true;
                                System.out.println("    \nleaving add product.");
                                break;
                        }
                    }
                    break;
                case 2:
                    boolean out = false;
                    while (!out) {
                        System.out.println();
                        System.out.println("    |====================Create users=====================|");
                        System.out.println("    |               1 - add new user                      |");
                        System.out.println("    |               2 - exit                              |");
                        System.out.println("    |=====================================================|");
                        System.out.print("    Enter your choice: ");

                        int choiceUser = getOption(keyboard);

                        switch (choiceUser){
                            case 1:
                                System.out.println();
                                System.out.println("    |=====================User's Type=====================|");
                                System.out.println("    |               1 - Administrator                     |");
                                System.out.println("    |               2 - Customer                          |");
                                System.out.println("    |=====================================================|");
                                System.out.print("    Enter your choice: ");

                                int selection = 0;

                                if(keyboard.hasNextInt()) {
                                    selection = keyboard.nextInt();
                                    keyboard.nextLine();
                                }else{
                                    System.out.println("    \n****INVALID CREDENTIALS. TRY AGAIN.****");
                                    keyboard.next();
                                    continue;
                                }
                                switch (selection){
                                    case 1:
                                        System.out.println();
                                        System.out.print("    enter name: ");
                                        String adminName = keyboard.nextLine();

                                        System.out.print("    enter email (\"@dealz.com\" recommended domain): ");
                                        String adminEmail = keyboard.nextLine();

                                        System.out.print("    enter password: ");
                                        String adminPassword = keyboard.nextLine();

                                        addUser(new Administrator(adminName, adminEmail, adminPassword));
                                        break;
                                    case 2:
                                        System.out.print("    enter name: ");
                                        String customerName = keyboard.nextLine();

                                        System.out.print("    enter email: ");
                                        String customerEmail = keyboard.nextLine();

                                        System.out.print("    enter password: ");
                                        String customerPassword = keyboard.nextLine();

                                        System.out.print("    enter adress: ");
                                        String customerAdress = keyboard.nextLine();

                                        addUser(new Customer(customerName, customerEmail, customerPassword, customerAdress));
                                        break;
                                }
                                break;
                            case 2:
                                out = true;
                                break;
                        }
                    }
                    break;
                case 3:
                    Product.display();
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
                    System.out.println("    \nLogout successful.");
                    break;
                default:
                    System.out.println("    \n****INVALID OPTION. TRY AGAIN.****");
            }
        }
    }
}