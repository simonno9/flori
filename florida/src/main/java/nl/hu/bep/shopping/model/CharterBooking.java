package nl.hu.bep.shopping.model;

public class CharterBooking {
    private int bookingId;
    private String customerName;
    private String customerEmail;
    private int charterId;
    private String bookingDate;
    private String specialRequests;
    private String image; // Add image field
    private int price;  // Add price field
    private String description;  // Add description field
    private String title;  // Add title field

    // Default constructor
    public CharterBooking() {}

    // Constructor with all fields
    public CharterBooking(int bookingId, String customerName, String customerEmail, int charterId, String bookingDate, String specialRequests, String image, int price, String description, String title) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.charterId = charterId;
        this.bookingDate = bookingDate;
        this.specialRequests = specialRequests;
        this.image = image;
        this.price = price;
        this.description = description;
        this.title = title;
    }

    // Getters and Setters for all fields, including new fields
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public String getCustomerEmail() { return customerEmail; }
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail; }

    public int getCharterId() { return charterId; }
    public void setCharterId(int charterId) { this.charterId = charterId; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public String getSpecialRequests() { return specialRequests; }
    public void setSpecialRequests(String specialRequests) { this.specialRequests = specialRequests; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}
