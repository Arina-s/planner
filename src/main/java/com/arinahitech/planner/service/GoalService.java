package com.arinahitech.planner.service;

import com.arinahitech.planner.model.Goal;
import java.util.List;

public interface GoalService {

    List<Goal> getAll();

    Goal getNewGoal();

    Goal getById(int id);

    void save(Goal goal);

    void deleteById(int id);

    void edit(Goal goal);

}
