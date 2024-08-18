package nl.hu.bep.shopping.model;

import org.w3c.dom.Text;

public class User {
    private String username;
    private String password;

    private String role;
    private String image;


    // Constructors
    public User() {
    }
    public User(String username, String password, String role, String image) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.image = image;
    }
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
    // Getters and Setters
    public String getImage() {return image;}
    public void setImage(String image) {
        this.image = image;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getRole() {
        return role;
    }

    public void setStatus(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
