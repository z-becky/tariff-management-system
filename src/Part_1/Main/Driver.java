package Part_1.Main;
//--------------------------------------------------------------
// Assignment 3
// Question 1
// Written by: Becky Zhang (40302813) & Rojin Niknejad (40264301)
//--------------------------------------------------------------
// This tariff management system serves to simulate the effects of international trade policies.
// By analyzing, calculating and adjusting tariffs on various goods, tracking and managing diplomatic disputes,
// and adding, removing, and resolving conflicts, this system simulates how tariffs impact global trade dynamics.

import Part_1.TariffManagement.TariffsManager;

public class Driver {
    public static void main(String[] args){

        //printing welcome message
        System.out.println("=================================================\n" +
                "|\tWelcome to the Tariff Management System\t\t|\n" +
                "|\tmade by Rojin Niknejad and Becky Zhang!\t\t|\n" +
                "=================================================");

        //creating the tariff manager objects
        TariffsManager tariffManager = new TariffsManager();

        //reading the product data from the file
        System.out.println("> Reading trade data...");
        tariffManager.readingTheFile();

        //updating to the new price after applying tariffs
        System.out.println("> Adjusting price according to the tariff rules...");
        tariffManager.applyTariffs();

        //sorting the products by alphabetical order
        System.out.println("> Sorting the products alphabetically...");
        tariffManager.sortProductByName();

        System.out.println("> Displaying the updated trade data...");
        //displaying the product information
        tariffManager.displayProduct();

        System.out.println("\n> Writing the updated trade data to the file...");
        //writing this updated data to the file UpdatedTradeData.txt
        tariffManager.writingNewFile();

        System.out.println("\n======= Thank you for using the Tariff Management System! =======");
    }
}
