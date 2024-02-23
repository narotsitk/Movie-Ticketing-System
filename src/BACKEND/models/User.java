package BACKEND.models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class User {
    public String email;
    public String username;
    public String password;
    private static User newUser = null;

    public int id;
    public User(String email,String username,String password){
        this.email = email;
        this.username = username;
        this.password = User.hashPassword(password);
    }
    public User(int id,String email,String username,String password){
        this.email = email;
        this.username = username;
        this.password = password;
        this.id = id;
    }

//    SHA-256 based hasing for security purposes
    public static String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            String encoded = Base64.getEncoder().encodeToString(hash);
            return encoded;
        }catch(NoSuchAlgorithmException ex) {
            return "";
        }
    }

//    global user variable
    public static User getInstance(){
        return newUser;
    }
    public static User createInstance(int id,String email,String username,String password){
        if(newUser == null){
            newUser = new User(id,email,username,password);
        }
        return newUser;
    }

//    for logout purposes
    public static boolean removeInstance(){
        if (newUser != null){
            newUser = null;
            return true;
        }
        return false;
    }
}
