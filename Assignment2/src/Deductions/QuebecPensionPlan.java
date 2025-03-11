package Deductions;

public class QuebecPensionPlan extends Deductions {
    @Override
    public double calculateTax(double annualGrossSalary) {
        //The QPP rate is 10.8% multiplied by the gross salary.
        //The maximum premium is $7,700.40, based on an annual gross salary of $71,300.
        if(annualGrossSalary <=71300){
            return payDeduction += annualGrossSalary* 0.108;
        }
        else{
            return payDeduction += 7700.40;
        }
    }
}
