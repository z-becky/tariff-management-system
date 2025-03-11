package Deductions;

public class FederalIncomeTax extends Deductions {
    @Override
    public double calculateTax(double annualGrossSalary) {
        //No tax on income less than $16,129
        if (annualGrossSalary < 16129){
            return payDeduction;
        }
        //15% on the annual taxable income ≥ $16,129 and ≤ $57,375
        if(annualGrossSalary >= 16129 && annualGrossSalary<=57375){
            return payDeduction += annualGrossSalary* 0.15;
        }
        //20.5% on the annual taxable income > $57,376 and ≤ $114,750.
        if(annualGrossSalary > 57376 && annualGrossSalary<=114750){
            return payDeduction += annualGrossSalary* 0.205;
        }
        //26% on the annual taxable income > $114,751 and ≤ $177,882.
        if(annualGrossSalary > 114751 && annualGrossSalary<=177882){
            return payDeduction += annualGrossSalary* 0.26;
        }
        //29% on the annual taxable income > $177,883 and ≤ $253,414.
        if(annualGrossSalary > 177883 && annualGrossSalary<=253414){
            return payDeduction += annualGrossSalary* 0.29;
        }
        //33% on the annual taxable income > $253,414
        else{
            return payDeduction += annualGrossSalary* 0.33;
        }
    }
}
