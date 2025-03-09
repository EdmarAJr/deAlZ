package br.edu.ifba.inf008.model;

import br.edu.ifba.inf008.shell.Authentication;
import static br.edu.ifba.inf008.shell.Authentication.hashPassword;
import static br.edu.ifba.inf008.data.FileRegister.users;

import java.io.Serializable;
import java.util.Objects;

public abstract class User implements Serializable {
    private static int idIncrement = 1;
    @SuppressWarnings("unused")
    private int id;
    private String name;
    private String email;
    private String password;
    private byte[] salt;

    public User(String name, String email, String password) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty())
            throw new IllegalArgumentException("    NAME, EMAIL, AND PASSWORD CANNOT BE EMPTY FIELDS.");

        if(users.putIfAbsent(email.trim(), this) == null){
            this.id = idIncrement++;
            this.name = name;
            this.email = email;
            this.salt = Authentication.generateSalt();
            this.password = hashPassword(password, this.salt).trim();
        } else {
            System.out.println("    EMAIL ALREADY REGISTERED!");
        }
    }

    public boolean isPasswordValid(String password) {
        String hashedPassword = hashPassword(password, this.salt);
        return Objects.equals(this.password, hashedPassword);
    }

    public void display (){
        System.out.println("    name: " + this.name);
        System.out.println("    email: " + this.email);
        System.out.println();
    }
    
    public String getEmail() {
        return email;
    }
}