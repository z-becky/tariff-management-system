package Part_1.TariffManagement;
//---------------------------------------------------------------
// Assignment 3
// Question 1
// Written by: Rojin Niknejad (40264301)
//----------------------------------------------------------------
// This product class serves as the blueprint to create product objects that
// belong to a country, a category and have a price on which tariffs will be applied on.

public class Product {
    // Class Attributes
    private  String productName;
    private  String country;
    private  String category;
    private  double price;

    // default constructor
    public Product(){};

    // Parametrized  constructor

    public Product(String productName , String country , String category , double price) {
        this.productName = productName;
        this.country = country;
        this.category = category;
        this.price = price;

    }
    // getter and setters


    public String getProductName() {
        return productName;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //toString method
    @Override
    public String toString () {

        String formattedPrice = String.format("%.1f",this.price);

        return "\nName of the Product: " + this.productName + " \nCountry: " + this.country +
                "\nCategory: " + this.category + "\nInitial Price: " + formattedPrice;
    };
}
