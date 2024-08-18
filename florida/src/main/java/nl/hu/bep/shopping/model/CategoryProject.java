package nl.hu.bep.shopping.model;

import java.util.List;

public class CategoryProject {
    private int id;
    private String name;
    private String description;
    private String image; // Using the image field
    private List<Project> projects; // Add this field to hold the list of projects

    // Default constructor
    public CategoryProject() {}

    // Parameterized constructor
    public CategoryProject(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }

    // Parameterized constructor including projects
    public CategoryProject(int id, String name, String description, String image, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.projects = projects;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
