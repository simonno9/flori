package nl.hu.bep.shopping.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Charter {
    private int charter_id;
    private String title;
    private String description;
    private int price;
    private String availability_status;
    private String image;
    private int deposit;  // New field for deposit
    private String people; // New field for people

    public Charter() {
    }

    public Charter(int charter_id, String title, String description, int price, String availability_status, String image, int deposit, String people) {
        this.charter_id = charter_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.availability_status = availability_status;
        this.image = image;
        this.deposit = deposit;
        this.people = people;
    }

    public Charter(String title, String description, int price, String availability_status, String image, int deposit, String people) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.availability_status = availability_status;
        this.image = image;
        this.deposit = deposit;
        this.people = people;
    }

    public Charter(int charter_id, String title, String description, int price, String availability_status, String image) {
        this.charter_id = charter_id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.availability_status = availability_status;
        this.image = image;
    }

    public Charter(String title, String description, int price, String availability_status, String image) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.availability_status = availability_status;
        this.image = image;
    }

    // Getters and Setters
    public int getCharter_Id() {
        return charter_id;
    }

    public void setCharter_id(int charter_id) {
        this.charter_id = charter_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getavailability_status() {
        return availability_status;
    }

    public void setavailability_status(String availability_status) {
        this.availability_status = availability_status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
