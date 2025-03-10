package Deductions;

public class QuebecParentalInsurancePlan extends Deductions {
    @Override
    void calculateTax() {
        //The QPIP rate is 0.494% for salaried workers.
        //The maximum premium is $494.12, based on an annual gross salary of $98,000.
        if(annualGrossSalary <= 98000){
            payDeduction += annualGrossSalary* 0.494;
        }
        else {
            payDeduction += 494.12;
        }
    }
}
