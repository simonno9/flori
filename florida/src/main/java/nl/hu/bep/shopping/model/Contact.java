package nl.hu.bep.shopping.model;

import java.util.List;

public class Contact {
    private int id;
    private String firstname;
    private String lastname;
    private String category;
    private String phonenumber;
    private String city;
    private String description;
    private List<String> imageUrls;

    // Constructors
    public Contact() {
    }

    public Contact(int id, String firstname, String lastname, String category, String phonenumber, String city, String description) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.category = category;
        this.phonenumber = phonenumber;
        this.city = city;
        this.description = description;
        this.imageUrls = imageUrls;
    }

    public Contact(int id, String firstname, String lastname, String category, String phonenumber, String city, String description, List<String> imageUrls) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.category = category;
        this.phonenumber = phonenumber;
        this.city = city;
        this.description = description;
        this.imageUrls = imageUrls;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
