package com.arinahitech.planner;

public class PlannerControl {

    private PlannedSubject plannedSubject;

    public PlannedSubject getPlannedSubject() {
        return plannedSubject;
    }

    public void setPlannedSubject(PlannedSubject plannedSubject) {
        this.plannedSubject = plannedSubject;
    }

    public PlannerControl(PlannedSubject plannedSubject) {
        this.plannedSubject = plannedSubject;
    }

    public void printPlannedSubject() {
        System.out.println("We are planning " + plannedSubject.getItemName());
    }
}
