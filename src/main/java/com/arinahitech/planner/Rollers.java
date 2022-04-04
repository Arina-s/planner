package com.arinahitech.planner;

import lombok.Data;

@Data
public class Rollers implements PlannedSubject {

    private int hours;

    @Override
    public String getItemName() {
        return "Rollers";
    }
}
