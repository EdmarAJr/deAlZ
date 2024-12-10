import java.util.HashMap;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Objects;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.SecureRandom;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class User{
    private static int idIncrement = 1;
    private int id;
    private String name;
    private String email;
    private String password;
    private byte[] salt;
    
    private static final int ITERATIONS = 65536;
    private static final int KEY = 256;
    private static final int SALT = 16;
    
    private static final HashMap<String, User> usersMap = new HashMap<>();
    
     
    static {
        new Administrator("Admin", "admin@dealz.com", "admin");
    }
    //apagar depois dos testes  
    static {
        new Customer("Cliente 1", "cliente@email.com", "1234", "Rua dos bobos");
    }
    
    public User(String name, String email, String password) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            System.out.println("Name, email, and password cannot be empty fields.");
            return;
        }
        if(usersMap.putIfAbsent(email, this) == null){
            this.id = idIncrement++;
            this.name = name;
            this.email = email;
            this.salt = generateSalt();
            this.password = hashPassword(password, salt);
        } else {
            System.out.println("email already registered!");
        }
    }
    
    public static User authenticate(String email, String password) {
        User user = usersMap.get(email);
        if (user != null && user.validatePassword(password)) {
            return user;  
        }
        return null;  
    }
   
    
    // private User(){
        
    // }
    
    public boolean validatePassword(String password) {
        String hashedPassword = hashPassword(password, this.salt);
        return Objects.equals(this.password, hashedPassword);
    }
    
    private byte[] generateSalt() {
        SecureRandom sr = new SecureRandom();
        //byte[] salt = new byte[SALT];
        salt = new byte[SALT];
        sr.nextBytes(salt);
        return salt;
    }

    private static String hashPassword(String password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY);
        Arrays.fill(password.toCharArray(), Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Erro ao gerar o hash da senha", e);
        } finally {
            spec.clearPassword();
        }
    }
    
    public void display (){
        System.out.println("id: " + this.id);
        System.out.println("name: " + this.name);
        System.out.println("email: " + this.email);
        //System.out.println("password: " + this.password); //a senha já está criptografada
        System.out.println();
    }
    
    // protected int getId(){
    //     return this.id;
    // }
    
    // protected String getName(){
    //     return this.name;
    // }
    
    // protected String getEmail(){
    //     return this.email;
    // }
    
}