package com.arinahitech.planner;

public class Book implements PlannedSubject {

    private String name;

    public Book(String name) {
        this.name = name;
    }

    @Override
    public String getItemName() {
        return name;
    }
}
