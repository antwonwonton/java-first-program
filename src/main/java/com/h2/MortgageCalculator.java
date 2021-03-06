package com.h2;
import com.h2.Utilities;
import java.text.DecimalFormat;

public class MortgageCalculator {
    private long loanAmount;
    private int termInYears;
    private float annualRate;
    private double monthlyPayment;

    public MortgageCalculator(long loanAmount, int termInYears, float annualRate){
        this.loanAmount = loanAmount;
        this.termInYears = termInYears;
        this.annualRate = annualRate;
    }

    private int getNumberOfPayments(){
        return termInYears*12;
    }
    private float getMonthlyInterestRate() {
        float interestRate = annualRate / 100;
        return interestRate/12;
    }
    public void calculateMonthlyPayment() {
        // M = P ( r * ( 1 + r )^n / ( ( 1 + r)^n  - 1 )
        // get M
        long P = loanAmount;
        float r = getMonthlyInterestRate();
        int n = getNumberOfPayments();
        double M = P * ( r * Math.pow( (1 + r) , n ) ) /  ( Math.pow( (1 + r) , n ) - 1 );
        this.monthlyPayment = M;
    }
    public String toString(){
        DecimalFormat df = new DecimalFormat("####0.00");
        return ("monthlyPayment: " + df.format(monthlyPayment));
    }
    public static void main(String[] args) {
        final   long    loanAmount      = Utilities.getLongValue(args[0]);
        final   int     termInYears     = Utilities.getIntValue(args[1]);
        final   float   annualRate      = Utilities.getFloatValue(args[2]);
        final  MortgageCalculator calculator = new MortgageCalculator( loanAmount, termInYears, annualRate);
        calculator.calculateMonthlyPayment();
        System.out.print( calculator.toString() + "\n" );

    }

}
