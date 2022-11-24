package com.wangwenjun.concurrent.chapter7_immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author malichun
 * @create 2022/11/23 0023 21:29
 */
public class ImmutableTest {
    private final int age;
    private final String name;
    private final List<String> list;

    public ImmutableTest(int age, String name) {
        this.age = age;
        this.name = name;
        this.list = new ArrayList<>();
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }
}
