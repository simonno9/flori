package nl.hu.bep.shopping.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FishingReport {
    private int id;
    private String title;
    private String description;
    private String date;
    private String image;

    // Default constructor (required for Jackson deserialization)
    public FishingReport() {
    }

    // Constructor with all fields (can be used for manual object creation)

    public FishingReport(int id, String title, String description, String date, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.date = date;
        this.image = image;
    }


    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
