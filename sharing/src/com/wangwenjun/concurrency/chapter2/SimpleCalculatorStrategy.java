package com.wangwenjun.concurrency.chapter2;

/**
 * @author malichun
 * @create 2022/11/17 0017 22:19
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy {
    private final static double SALARY_RATE = 0.1;
    private final static double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
