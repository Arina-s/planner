package com.arinahitech.planner.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Goal {

    private int id;
    private String name;
    private String priority;
    private Estimation estimation;

}
