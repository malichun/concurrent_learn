package com.wangwenjun.concurrency.chapter2;

/**
 * @author malichun
 * @create 2022/11/17 0017 22:09
 */
@FunctionalInterface
public interface CalculatorStrategy {
    double calculate(double salary, double bonus);
}
