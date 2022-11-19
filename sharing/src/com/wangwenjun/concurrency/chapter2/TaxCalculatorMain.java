package com.wangwenjun.concurrency.chapter2;

/**
 * @author malichun
 * @create 2022/11/16 0016 22:09
 */
public class TaxCalculatorMain {
    public static void main(String[] args) {
     /*    TaxCalculator calculator = new TaxCalculator(10000d, 2000d){
            @Override
            public double calcTax() {
                return getSalary() * 0.1 + getBonus() * 0.15;
            }
        };
        double tax = calculator.calculate(); // 税
        System.out.println(tax);
      */
        TaxCalculator calculator = new TaxCalculator(10000d, 2000d, (s,b) -> s * 0.1 +  b*0.15);


        System.out.println(calculator.calculate());


    }
}
