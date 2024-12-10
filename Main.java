// /**
//  * já existe um usuário "Admin", "admin@dealz.com", "admin"
//  * para criar um usuário administrador o email obrigatoriamente deve conter @dealz.com, caso contrário será um usuário ustomer 
//  * a senha é ocultada enquanto digita, não é um problema no terminal
//  */

// import java.util.Scanner;
// public class Main {
//     public static void main(String[] args) {
//         Scanner keyboard = new Scanner(System.in);
        
//         // Customer authenticatedCustomer = (Customer) authenticatedUser;
//         // Order order = new Order(authenticatedCustomer); // Certifique-se de que o construtor de Order aceita a inicialização
        
//         //Administrator admin2 = new Administrator("Admin", "admin@example.com", "password");
//         //admin2.display();
        
//         boolean exit = false;
//         while (!exit) {
//             System.out.println("\nWelcome to the DeAlZ e-commerce system\n");
//             System.out.println("    |==================|");
//             System.out.println("    |    1 - Login     |");
//             System.out.println("    |    2 - Exit      |");
//             System.out.println("    |==================|");
//             System.out.print("    Enter your choice: ");

//             int option = 0;

//             // Verificação adicional para garantir que a entrada seja um número inteiro.
//             if(keyboard.hasNextInt()) {
//                 option = keyboard.nextInt();
//                 keyboard.nextLine(); // Consumir a nova linha
//             }else{
//                 System.out.println("\nInvalid input. Please enter a valid number.");
//                 keyboard.next(); // Consome a entrada inválida
//                 continue;
//             }

//             switch (option) {
//                 case 1: {
//                     System.out.println("    |==================|");
//                     System.out.print("     Enter email: ");
//                     String email = keyboard.nextLine();
//                     System.out.print("     Enter password: ");
//                     String password = keyboard.nextLine();
//                     User authenticatedUser = User.authenticate(email, password);
                    
//                     if (authenticatedUser == null) {
//                         System.out.println("\nInvalid credentials. Try again.");
//                     }else if (authenticatedUser instanceof Administrator) {
//                         new AdminMenu((Administrator) authenticatedUser).showMenu();
//                     }
//                     else {
//                         new CustomerMenu((Customer) authenticatedUser).showMenu();
//                         //new CustomerMenu(product, order, authenticatedCustomer).showMenu();
//                     }
//                     break;
//                 }
//                 case 2: {
//                     System.out.println("\nGoodbye!");
//                     exit = true;
//                     break;
//                 }
//                 default:
//                     System.out.println("Invalid option. Try again.");
//             }
//         }

//         keyboard.close();
//     }
// }

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
//import java.util.ArrayList;
import java.util.Scanner;
import java.io.Console;

public class Main {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Console console = System.console();  // Obtém o console, se disponível
        
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the DeAlZ e-commerce system\n");
            System.out.println("    |==================|");
            System.out.println("    |    1 - Login     |");
            System.out.println("    |    2 - Exit      |");
            System.out.println("    |==================|");
            System.out.print("    Enter your choice: ");

            int option = 0;

            // Verificação adicional para garantir que a entrada seja um número inteiro.
            if(keyboard.hasNextInt()) {
                option = keyboard.nextInt();
                keyboard.nextLine(); // Consumir a nova linha
            } else {
                System.out.println("\nInvalid input. Please enter a valid number.");
                keyboard.next(); // Consome a entrada inválida
                continue;
            }

            switch (option) {
                case 1: {
                    System.out.println("    |==================|");
                    System.out.print("     Enter email: ");
                    String email = keyboard.nextLine();
                    System.out.print("     Enter password: ");
                    
                    // Verifique se o console está disponível
                    String password = null;
                    if (console != null) {
                        // Usando console para obter a senha como asteriscos
                        char[] passwordArray = console.readPassword();
                        password = new String(passwordArray);
                    } else {
                        // Caso o console não esteja disponível, usar o Scanner como alternativa
                        password = keyboard.nextLine();
                    }
                    
                    User authenticatedUser = User.authenticate(email, password);
                    
                    if (authenticatedUser == null) {
                        System.out.println("\nInvalid credentials. Try again.");
                    } else if (authenticatedUser instanceof Administrator) {
                        new AdminMenu((Administrator) authenticatedUser).showMenu();
                    } else {
                        new CustomerMenu((Customer) authenticatedUser).showMenu();
                    }
                    break;
                }
                case 2: {
                    System.out.println("\nGoodbye!");
                    exit = true;
                    break;
                }
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        keyboard.close();

        // FileOutputStream fos = new FileOutputStream("dealz.dat");
        // ObjectOutputStream oos = new ObjectOutputStream(fos);
        // oos.writeObject(students);
        // fos.close();

        FileInputStream fis = new FileInputStream("dealz.dat");
        ObjectInputStream ois = new ObjectInputStream(fis);
        //ArrayList<Student> myStudents = (ArrayList<Student>) ois.readObject();
        fis.close();
    }
}
