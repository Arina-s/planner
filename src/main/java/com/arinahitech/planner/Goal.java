package com.arinahitech.planner;

import org.springframework.stereotype.Component;

@Component
public class Goal {

    private String name;

    public Goal() {
    }

    public Goal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printGoal() {
        if (name != null) {
            System.out.println("My goal is " + name + "!");
        } else {
            System.out.println("There is no goal yet.");
        }
    }
}
