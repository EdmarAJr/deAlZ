package br.edu.ifba.inf008.shell;

import br.edu.ifba.inf008.model.User;
import static br.edu.ifba.inf008.data.FileRegister.users;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class Authentication {
    private static final int ITERATIONS = 65536;
    private static final int KEY = 256;
    private static final int SALT = 16;

    public static String hashPassword(String password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY);
        Arrays.fill(password.toCharArray(), Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("    ERROR GENERATING PASSWORD HASH\n", e);
        } finally {
            spec.clearPassword();
        }
    }

    public static byte [] generateSalt() {
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[SALT];
        sr.nextBytes(salt);
        return salt;
    }

    public static User authenticate(String email, String password) {
        User authenticatedUser = users.get(email);
        if (authenticatedUser != null && authenticatedUser.isPasswordValid(password)) {
               return authenticatedUser;
        }
           return null;
    }
}
