package Deductions;

public class ProvincialIncomeTax extends Deductions {
    @Override
    void calculateTax() {
        //14% rate applies to annual taxable income > $18,571 and ≤ $53,255.
        if(annualGrossSalary > 18571 && annualGrossSalary<=53255){
            payDeduction += annualGrossSalary* 0.14;
        }
        //19% rate applies to annual taxable income > $53,255 and ≤ $106,495.
        if(annualGrossSalary > 53255 && annualGrossSalary<=106495){
            payDeduction += annualGrossSalary* 0.19;
        }
        //24% rate applies to annual taxable income > $106,495 and ≤ $129,590.
        if(annualGrossSalary > 106495 && annualGrossSalary<=129590){
            payDeduction += annualGrossSalary* 0.24;
        }
        //25.75% rate applies to annual taxable income > $129,590.
        if(annualGrossSalary > 129590){
            payDeduction += annualGrossSalary* 0.2575;
        }
    }
}
