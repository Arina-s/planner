package com.arinahitech.planner.dao;

import com.arinahitech.planner.model.Goal;
import java.util.List;

public interface GoalDao {

    List<Goal> getAll();

    Goal getNewGoal();

    Goal getById(int id);

    void save(Goal goal);

    void deleteById(int id);

    void edit(Goal goal);

}
