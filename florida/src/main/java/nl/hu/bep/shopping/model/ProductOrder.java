package nl.hu.bep.shopping.model;

public class ProductOrder {
    private Integer orderId;
    private Integer orderQuantity;
    private String orderDate;
    private String productName;
    private String description;
    private String sizes;
    private String colors;
    private Integer productId;

    // No-argument constructor
    public ProductOrder() {
    }

    // Constructor used when creating a new ProductOrder
    public ProductOrder(Integer orderQuantity, String orderDate, Integer productId, Integer orderId) {
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.productId = productId;
        this.orderId = orderId;

    }

    // Constructor used when retrieving a ProductOrder from the database


    public ProductOrder(Integer orderId, Integer orderQuantity, String orderDate, String productName, String description, String sizes, String colors, Integer productId) {
        this.productId = productId;
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
        this.productName = productName;
        this.description = description;
        this.sizes = sizes;
        this.colors = colors;
    }

    public ProductOrder(int orderQuantity) {
        this.orderQuantity = orderQuantity;

    }

    // Getters and setters
    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }
}
