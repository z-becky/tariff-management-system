package Part_2.Main;
//--------------------------------------------------------------
// Assignment 3
// Question 2
// Written by: Becky Zhang (40302813) & Rojin Niknejad (40264301)
//--------------------------------------------------------------
//This tariff management system serves to simulate the effects of international trade policies.
//By analyzing, calculating and adjusting tariffs on various goods, tracking and managing diplomatic disputes,
//and adding, removing, and resolving conflicts, this system simulates how tariffs impact global trade dynamics.


import Part_2.TariffManagement.Tariff;
import Part_2.TariffManagement.TariffList;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TradeManager {
    public static void main(String[] args) {
        //printing welcome message
        System.out.println("=================================================\n" +
                "|\tWelcome to the Tariff Management System\t\t|\n" +
                "|\tmade by Rojin Niknejad and Becky Zhang!\t\t|\n" +
                "=================================================");

        //create 2 empty tariff lists
        TariffList tariffList1 = new TariffList();
        TariffList tariffList2 = new TariffList();

        System.out.println("> Reading tariff data...");
        //read the contents of Tariffs.txt
        try {
            File tariffsFile = new File("src/Part_2/Tariffs.txt");
            Scanner tariffsReader = new Scanner(tariffsFile);

            while (tariffsReader.hasNextLine()) {
                //read each line from the file
                String line = tariffsReader.nextLine();

                //split each line into its designated parts in an array
                String[] parts = line.split(" ");

                String destinationCountry = parts[0];
                String originCountry = parts[1];
                String productCategory = parts[2];
                double minTariff = Double.parseDouble(parts[3]);

                //set a condition for a list that does not already contain the tariff to avoid duplicates
                if (!(tariffList1.contains(originCountry,destinationCountry,productCategory))){
                    //add the tariff to the start of the list if the condition is met with addToStart()
                    tariffList1.addToStart(new Tariff(destinationCountry, originCountry, productCategory, minTariff));
                }
            }
            //close the file
            tariffsReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Tariffs.txt not found!");
        }

        System.out.println("> Reading trade requests...\n" +
                "> Evaluating trade requests...\n");
        try {
            File tradeFile = new File("src/Part_2/TradeRequests.txt");
            Scanner requestReader = new Scanner(tradeFile);

            while(requestReader.hasNextLine()){
                //read each line from the file
                String line = requestReader.nextLine();

                //split each line into its designated parts in an array
                String[] parts = line.split(" ");

                String requestID = parts[0];
                String originCountry = parts[1];
                String destinationCountry = parts[2];
                String productCategory = parts[3];
                double tradeValue = Double.parseDouble(parts[4]);
                double proposedTariff = Double.parseDouble(parts[5]);

                System.out.println(requestID + tariffList1.evaluateRequest(originCountry,destinationCountry,productCategory,tradeValue,proposedTariff));


            }
            //close trade requests file
            requestReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("TradeRequests.txt not found!");
        }

        //tariff search
        Scanner readInput = new Scanner(System.in);

        int choice;
        do {
            System.out.print("Would you like to search for a tariff?\n" +
                    "\t1. Yes" +
                    "\n\t2. No" +
                    "\nEnter your choice > ");

            try {
                choice = readInput.nextInt();
                readInput.nextLine();

                if (choice == 1) {
                    System.out.print("\n======== Search for a tariff ========\n" +
                            "Please enter the following information >\n" +
                            "Destination Country: ");
                    String searchDestination = readInput.nextLine();

                    System.out.print("Origin Country: ");
                    String searchOrigin = readInput.nextLine();

                    System.out.print("Product Category: ");
                    String searchCategory = readInput.nextLine();

                    tariffList1.find(searchOrigin, searchDestination, searchCategory);
                } else if (choice != 2) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.\n");
                readInput.nextLine();
                choice = 0;
            }
        } while (choice != 2);

        //method testing
        System.out.println("\n======== Proceeding with Method Testing ========\n" +
                "> Adding to a new tariff list...");

        //adding to the tariff list
        tariffList2.addToStart(new Tariff("Saudi Arabia","India","Agriculture",5));
        tariffList2.addToStart(new Tariff("Brazil","Japan","Automobiles",15));
        tariffList2.addToStart(new Tariff("South Korea","Bangladesh","Textile",4));

        //print list
        System.out.println("\n" + tariffList2);

        System.out.print("> Testing insertAtIndex() method...\n" +
                "Invalid index: ");

        tariffList2.insertAtIndex(new Tariff("USA","China","Agriculture",30), 6);
        tariffList2.insertAtIndex(new Tariff("USA","China","Agriculture",30), 1);

        System.out.println("Valid tariff inserted at index 1 of the tariff list.");

        System.out.println("\n" + tariffList2);

        System.out.print("> Testing deleteFromIndex() method...\n" +
                "Invalid index: ");
        tariffList2.deleteFromIndex(10);
        tariffList2.deleteFromIndex(2);
        System.out.println("Valid index. Tariff deleted at index 2 of the tariff list.");

        System.out.println("\n" + tariffList2);

        System.out.println("> Testing deleteFromStart() method...");
        tariffList2.deleteFromStart();
        System.out.println("Head is not null. Tariff deleted at the start of the tariff list.");

        System.out.println("\n" + tariffList2);

        System.out.print("> Testing replaceAtIndex() method...\n" +
                "Invalid index: ");
        tariffList2.replaceAtIndex(new Tariff("China","USA","Electronics",25),10);
        tariffList2.replaceAtIndex(new Tariff("China","USA","Electronics",25),1);
        System.out.println("Valid index. Tariff replaced at index 1 of the tariff list.");

        System.out.println("\n" + tariffList2);

        System.out.print("> Testing find() method..." +
                "\nNon-existent tariff: ");
        tariffList2.find("Japan","USA","Manufacturing");
        System.out.println("Valid tariff found:");
        tariffList2.find("China","USA","Agriculture");

        System.out.println("\n> Testing contains() method..." +
                "\nFor a tariff that is not in the list, return " + tariffList2.contains("Japan","Japan","Electronics") +
                "\nFor a tariff that is in the list, return " + tariffList2.contains("China","USA","Agriculture"));

        System.out.println("\n> Testing equals() method..."+
                "\nFor two lists with different TariffNodes, return " + tariffList1.equals(tariffList2)
                + "\n> Modifying the lists to make them equal...");
        tariffList2.addToStart(new Tariff("Germany","USA","Automobiles",10));

        System.out.println("For two lists containing similar TariffNodes, return " + tariffList1.equals(tariffList2)
        + "\n");

        System.out.println("> Testing deleteFromStart() method on an empty list...");
        for (int i = 0; i <4; i++){
            tariffList2.deleteFromStart();
        }

        System.out.println("\n======= Thank you for using the Tariff Management System! =======");
    }
}
