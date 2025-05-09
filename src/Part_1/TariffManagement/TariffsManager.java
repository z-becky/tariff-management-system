package Part_1.TariffManagement;
//-------------------------------------------------
// Assignment 3
// Question 1
// Written by: Becky Zhang (40302813) & Rojin Niknejad (40264301)
//-------------------------------------------------
// This tariffs manager class contains methods to read from the files containing the trade date,
// to apply tariffs, to sort the products and to write the updated trade data in a file.

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;

public class TariffsManager {

    // create an arrayList
    private ArrayList<Product> products;

    // constructor & initializing the arrayList
    public TariffsManager() {
        products = new ArrayList<>();
    }

    // reading the file tradeData.txt
    public void readingTheFile() {
        try {
            File file = new File("src/Part_1/TradeData.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                //reading each line of the file
                String line = scanner.nextLine();

                // creating a string array to split the data by comma
                String[] parts = line.split(",");

                String productName = parts[0];
                String country = parts[1];
                String category = parts[2];
                double initialPrice = Double.parseDouble(parts[3]);


                // create a product object and add it to the arraylist
                Product product = new Product(productName, country, category, initialPrice);
                products.add(product);

            }
            // closing the file after reading
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }

    // create a new method to adjust the tariffs
    public void applyTariffs() {

        // loops through the products and retrieves them
        for (int i = 0; i < products.size(); i++) {

            Product product = products.get(i);
            String country = product.getCountry();
            String category = product.getCategory();
            double price = product.getPrice();

            switch (country) {

                case "China":
                    price *= 1.25;
                    break;

                case "USA":
                    if (category.equalsIgnoreCase("Electronics")) {
                        price *= 1.10;
                    }
                    break;

                case "Japan":
                    if (category.equalsIgnoreCase("Automobile")) {
                        price *= 1.15;
                    }
                    break;

                case "India":
                    if (category.equalsIgnoreCase("Agriculture")) {
                        price *= 1.05;
                    }
                    break;

                case "South Korea":
                    if (category.equalsIgnoreCase("Electronics")) {
                        price *= 1.08;
                    }
                    break;

                case "Saudi Arabia":
                    if (category.equalsIgnoreCase("Energy")) {
                        price *= 1.12;
                    }
                    break;

                case "Germany":
                    if (category.equalsIgnoreCase("Manufacturing")) {
                        price *= 1.06;
                    }
                    break;

                case "Bangladesh":
                    if (category.equalsIgnoreCase("Textile")) {
                        price *= 1.04;
                    }
                    break;

                case "Brazil":
                    if (category.equalsIgnoreCase("Agriculture")) {
                        price *= 1.09;
                    }
                    break;
            }
            //update product price
            product.setPrice(price);
        }

    }
    // display the products
    public void displayProduct() {

        for (Product product : products) {
            System.out.println(product);
        }

    }

    // sorting based on product name's alphabetically
    public void sortProductByName() {

        products.sort(new Comparator<Product>() {

            @Override
            public int compare(Product p1, Product p2) {
                return p1.getProductName().compareTo(p2.getProductName());

            }

        });

    }

    // write the updated data into a new file
    public void writingNewFile ()  {

        try {

            FileWriter newFile = new FileWriter("src/Part_1/UpdatedTradeData.txt");
            PrintWriter printWriter = new PrintWriter(newFile);

            //Steel,China,Raw Material,1250.0
            for (int i = 0 ; i < products.size() ; i++) {
                Product product = products.get(i);
                printWriter.print(product.getProductName() + "," + product.getCountry() + "," +
                        product.getCategory() + ",");
                printWriter.printf("%.1f",product.getPrice());
                printWriter.println();
            }
            printWriter.close();

        } catch (IOException e ) {
            System.out.println("An error has occurred!");
        }
    }


}
