package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.interfaces.IMenu;
import br.edu.ifba.inf008.model.User;


import java.util.Scanner;

public class UserUi {
    private Scanner keyboard;

    public UserUi() {
        this.keyboard = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n****WELCOME TO THE DEALZ E-COMMERCE SYSTEM.****");
            System.out.println("    |==================|");
            System.out.println("    |    1 - Login     |");
            System.out.println("    |    2 - Exit      |");
            System.out.println("    |==================|");
            System.out.print("    Enter your choice: ");

            int option = getOption();

            switch (option) {
                case 1:
                    handleLogin();
                    break;
                case 2:
                    System.out.println("\n****GOODBYE!****");
                    keyboard.close();
                    exit = true;
                    break;
                default:
                    System.out.println("\n****INVALID OPTION. TRY AGAIN.****");
            }
        }
    }

    private void handleLogin() {
        System.out.print("    Enter email: ");
        String email = keyboard.nextLine().trim();
        System.out.print("    Enter password: ");
        String password = keyboard.nextLine().trim();

        User auth = Authentication.authenticate(email.trim(), password.trim());

        if (auth instanceof IMenu) {
            ((IMenu) auth).showMenu();
        } else {
            System.out.println("\n****INVALID CREDENTIALS. TRY AGAIN.****");
        }
    }

    private int getOption() {
        int option = 0;
        if (keyboard.hasNextInt()) {
            option = keyboard.nextInt();
            keyboard.nextLine();
        } else {
            System.out.println("\n****INVALID INPUT. PLEASE ENTER A VALID NUMBER.****");
            keyboard.next();
        }
        return option;
    }
}