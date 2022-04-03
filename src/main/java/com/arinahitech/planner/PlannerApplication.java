package com.arinahitech.planner;

public class PlannerApplication {

    public static void main(String[] args) {
        PlannerControl plannerControl = new PlannerControl(new Book("Want Can Should"));
        plannerControl.printPlannedSubject();

        plannerControl.setPlannedSubject(new Rollers());
        plannerControl.printPlannedSubject();
    }

}
