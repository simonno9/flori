package nl.hu.bep.shopping.model;

public class Product {
    private int id;
    private String product_name;
    private String description;
    private Integer quantity;
    private String status;
    private String colors;
    private String sizes;

    private Integer price;

    private String image;

    public Product() {
    }
    public Product(int id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }


    public Product(Integer quantity) {
        this.quantity = quantity;
    }

    public Product(int id, Integer price, String product_name, String description, Integer quantity, String status, String colors, String sizes, String image) {
        this.id = id;
        this.price = price;
        this.product_name = product_name;
        this.description = description;
        this.quantity = quantity;
        this.status = status;
        this.colors = colors;
        this.sizes = sizes;
        this.image = image;
    }
    public Integer getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    // Getters and setters for the new field
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Other getters and setters remain the same...

    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }
}
