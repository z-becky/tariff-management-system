package Employee;//-------------------------------------------------
// Assignment 2
// Written by: Becky Zhang (40302813)
//-------------------------------------------------
//This Employee.Employee class serves as a blueprint for creating employees and their payroll information,
//containing methods that can calculate their gross salary.

public class Employee {
    protected int employeeNumber;
    protected String firstName;
    protected String lastName;
    protected double hoursWorked;
    protected double hourlyWage;
    protected double annualGrossSalary;

    //default constructor
    public Employee(){}

    //parametrized constructor
    public Employee(int employeeNumber, String firstName, String lastName, double hoursWorked, double hourlyWage, double annualGrossSalary) {
        this.employeeNumber = employeeNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hoursWorked = hoursWorked;
        this.hourlyWage = hourlyWage;
        this.annualGrossSalary = annualGrossSalary;
    }

    //copy constructor
    public Employee(Employee other){
        employeeNumber = other.employeeNumber;
        firstName = other.firstName;
        lastName = other.lastName;
        hoursWorked = other.hoursWorked;
        hourlyWage = other.hourlyWage;
        annualGrossSalary = other.annualGrossSalary;
    }

    //getters
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public double getAnnualGrossSalary() {
        return annualGrossSalary;
    }

    //setters
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    public void setAnnualGrossSalary(double annualGrossSalary) {
        this.annualGrossSalary = annualGrossSalary;
    }

    //method to calculate annual gross salary
    public void calculateAnnualGrossSalary(double hoursWorked, double hourlyWage){
        annualGrossSalary = hoursWorked*hourlyWage*52;
    }

    public boolean equals(Employee obj) {
        return super.equals(obj) && employeeNumber == obj.employeeNumber;
    }

    public String toString(){
        return employeeNumber + " " + firstName + " " + lastName + " " + hoursWorked + " " + hourlyWage + " " + annualGrossSalary;
    }
}
