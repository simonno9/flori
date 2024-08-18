package nl.hu.bep.shopping.model;

import java.util.List;

public class Project {
    private int id;                   // The unique ID of the project
    private String name;              // The name of the project
    private String description;       // The description of the project
    private int categoryId;           // The category ID of the project
    private String img;               // Main image link
    private List<String> imageLinks;  // List of additional image links related to the project
    private List<Review> reviews;     // List of reviews related to the project

    // Default constructor
    public Project() {}

    // Parameterized constructor for all attributes
    public Project(int id, String name, String description, int categoryId, String img, List<String> imageLinks, List<Review> reviews) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.img = img;
        this.imageLinks = imageLinks;
        this.reviews = reviews;
    }

    // Parameterized constructor for main image
    public Project(int id, String name, String description, int categoryId, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryId = categoryId;
        this.img = img;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(List<String> imageLinks) {
        this.imageLinks = imageLinks;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
