package Part_2.TariffManagement;

//-------------------------------------------------
// Assignment 3
// Question 2
// Written by: Rojin Niknejad (40264301)
//-------------------------------------------------

public class Tariff {
    protected String destinationCountry;
    protected String originCountry;
    protected String productCategory;
    protected double minimumTariff;

    // parameterized constructor
    public Tariff(String destinationCountry, String originCountry, String productCategory, double minimumTariff) {
        this.destinationCountry = destinationCountry;
        this.originCountry = originCountry;
        this.productCategory = productCategory;
        this.minimumTariff = minimumTariff;
    }

    // copy constructor
    public Tariff (Tariff other) {
        this.destinationCountry = other.destinationCountry;
        this.originCountry = other.originCountry;
        this.productCategory = other.productCategory;
        this.minimumTariff = other.minimumTariff;
    }

    // getters and setters
    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public double getMinimumTariff() {
        return minimumTariff;
    }

    public void setMinimumTariff(double minimumTariff) {
        this.minimumTariff = minimumTariff;
    }

    // clone method
    @Override
    public Tariff clone() {
        return new Tariff(this.destinationCountry , this.originCountry , this.productCategory , this.minimumTariff);
    }

    // equal and toString methods
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        Tariff other = (Tariff) obj;
        return this.destinationCountry.equals(other.destinationCountry) && this.originCountry.equals(other.originCountry) &&
                this.productCategory.equals(other.productCategory) && this.minimumTariff == other.minimumTariff;
    }

    @Override
    public String toString () {

        return "Destination: " + destinationCountry + "\nOrigin Country: " + originCountry +
                "\nProduct Category: " + productCategory + "\nMinimum Tariff: " + minimumTariff;
    }
}
