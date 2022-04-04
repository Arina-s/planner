package com.arinahitech.planner;

import org.springframework.stereotype.Component;

@Component
public class Dream implements Ambition {

    private String name;

    public Dream() {
    }

    public Dream(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printDream() {
        if (name != null) {
            System.out.println("My dream is " + name + "!");
        } else {
            System.out.println("There is no dream yet.");
        }
    }

    @Override
    public void printAmbition() {
        printDream();
    }
}
