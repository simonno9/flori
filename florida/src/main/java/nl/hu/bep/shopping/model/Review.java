package nl.hu.bep.shopping.model;

public class Review {
    private int id;
    private String description;
    private int projectId;
    private String titel;
    private int rating;
    private String projectImage;
    private String categoryName;

    // Default constructor
    public Review() {}

    // Parameterized constructor
    public Review(int id, String description, int projectId, String titel, int rating, String projectImage, String categoryName) {
        this.id = id;
        this.description = description;
        this.projectId = projectId;
        this.titel = titel;
        this.rating = rating;
        this.projectImage = projectImage;
        this.categoryName = categoryName;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getProjectId() { return projectId; }
    public void setProjectId(int projectId) { this.projectId = projectId; }

    public String getTitel() { return titel; }
    public void setTitel(String titel) { this.titel = titel; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getProjectImage() { return projectImage; }
    public void setProjectImage(String projectImage) { this.projectImage = projectImage; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
