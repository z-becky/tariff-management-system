package Deductions;

public class EmployementInsurance extends Deductions {
    @Override
    void calculateTax() {

        if(annualGrossSalary <= 65700){
            payDeduction += (int)annualGrossSalary/100 * 1.64;
        }
        else{
            payDeduction += 1077.48;
        }
    }
}
