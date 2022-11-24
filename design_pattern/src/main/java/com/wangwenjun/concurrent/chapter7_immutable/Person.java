package com.wangwenjun.concurrent.chapter7_immutable;

/**
 * @author malichun
 * @create 2022/11/23 0023 21:12
 */
public final class Person {
    private final String name;
    private final String address;

    public Person(final String name,final String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", address='" + address + '\'' +
            '}';
    }
}
