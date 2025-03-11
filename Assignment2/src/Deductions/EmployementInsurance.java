package Deductions;

public class EmployementInsurance extends Deductions {

    @Override
    public double calculateTax(double annualGrossSalary) {

        if(annualGrossSalary <= 65700){
            return payDeduction += (int)annualGrossSalary/100 * 1.64;
        }
        else{
            return payDeduction += 1077.48;
        }
    }
}
