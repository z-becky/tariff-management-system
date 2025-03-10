package Deductions;

public class QuebecPensionPlan extends Deductions {
    @Override
    void calculateTax() {
        //The QPP rate is 10.8% multiplied by the gross salary.
        //The maximum premium is $7,700.40, based on an annual gross salary of $71,300.
        if(annualGrossSalary > 18571 && annualGrossSalary<=53255){
            payDeduction += annualGrossSalary* 0.14;
        }
    }
}
