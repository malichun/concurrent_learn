package com.wangwenjun.concurrency.chapter2;

/**
 * @author malichun
 * @create 2022/11/16 0016 22:07
 */
public class TaxCalculator {

    private final  double salary;

    private final double bonus;


    private CalculatorStrategy calculatorStrategy;

    public TaxCalculator(double salary, double bonus, CalculatorStrategy calculatorStrategy) {
        this.salary = salary;
        this.bonus = bonus;
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calcTax(){
        return calculatorStrategy.calculate(salary,bonus);
    }

    public double calculate(){
        return calcTax();
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }
}
