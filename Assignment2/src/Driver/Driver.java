package Driver;
import Employee.*;
import Deductions.*;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.FileNotFoundException;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner inputStream = null;
        PrintWriter outputStream = null;

        outputStream = new PrintWriter("payrollError.txt");

        inputStream = new Scanner(new FileInputStream("payroll.txt"));


        System.out.println("> Opening file payroll... \n" +
                "> Reading file payroll...");

        int employeeCount = 0;
        int errorCount = 0;
        Employee[] employeesArray = new Employee[30];

        while (inputStream.hasNextLine()) {
            String line = inputStream.nextLine();
            try {
                Scanner lineScanner = new Scanner(line);
                long employeeNumber = lineScanner.nextLong();
                String firstName = lineScanner.next();
                String lastName = lineScanner.next();
                double hoursWorked = lineScanner.nextDouble();
                double hourlyWage = lineScanner.nextDouble();

                if(hourlyWage<15.75){
                    throw new InputMismatchException();
                }

                employeeCount++;
                employeesArray[employeeCount] = new Employee(employeeNumber, firstName, lastName, hoursWorked, hourlyWage);
                employeesArray[employeeCount].calculateAnnualGrossSalary();

            }catch(InputMismatchException e){
                errorCount++;
                outputStream.println(line);
            }
        }
        outputStream.close();

        System.out.println("> Error lines found in file payroll\n");

        inputStream = new Scanner(new FileInputStream("payrollError.txt"));

        while(inputStream.hasNextLine()){
            System.out.println(inputStream.nextLine());
        }

        System.out.println("\n> "+ (employeeCount + errorCount) + " lines read from file payroll\n" +
                "> " + errorCount + " lines written to error file\n");

        System.out.println("> Calculating Deductions");

        for (int i=0; i<employeeCount;i++){
            if (employeesArray[i] != null) {
                employeesArray[i].setPayDeduction(
                        new EmployementInsurance().calculateTax(employeesArray[i].getAnnualGrossSalary())
                        + new FederalIncomeTax().calculateTax(employeesArray[i].getAnnualGrossSalary())
                        + new ProvincialIncomeTax().calculateTax(employeesArray[i].getAnnualGrossSalary())
                        + new QuebecParentalInsurancePlan().calculateTax(employeesArray[i].getAnnualGrossSalary())
                        + new QuebecPensionPlan().calculateTax(employeesArray[i].getAnnualGrossSalary())

                );

                employeesArray[i].setNetSalary(
                        employeesArray[i].getAnnualGrossSalary() - employeesArray[i].getPayDeduction()
                );
            }
        }

        outputStream = new PrintWriter("payrollReport.txt");
        outputStream.println("\t\t\t\t\t\t\t\t\tiDroid Solutions" +
                "\n\t\t\t\t\t\t\t\t\t-----------------");
        outputStream.printf("%-15s %-15s %-15s %-15s %-15s %-15s",
                "Employee", "First Name", "Last Name", "Gross Salary", "Deductions", "Net Salary\nNumber" +
                        "\n--------------------------------------------------------------------------------------------\n");

        for(int i=0; i<=employeeCount;i++){
            if(employeesArray[i]!=null) {
                outputStream.printf("%-15s %-15s %-15s %15.2f %15.2f %15.2f",
                        "\n" + employeesArray[i].getEmployeeNumber(),
                        employeesArray[i].getFirstName(),
                        employeesArray[i].getLastName(),
                        employeesArray[i].getAnnualGrossSalary(),
                        employeesArray[i].getPayDeduction(),
                        employeesArray[i].getNetSalary());
            }
        }

        outputStream.close();

    }
}
