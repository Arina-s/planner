package com.arinahitech.planner.dao.impl;

import com.arinahitech.planner.dao.GoalDao;
import com.arinahitech.planner.model.Estimation;
import com.arinahitech.planner.model.Goal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class GoalDaoImpl implements GoalDao {

    private List<Goal> goals;
    private int index = 0;

    public GoalDaoImpl() {
        goals = new ArrayList<>();
        goals.add(new Goal(++index, "English B2", "high", new Estimation("6 months", 8)));
        goals.add(new Goal(++index, "Move to another country", "high", new Estimation("1 month", 4)));
        goals.add(new Goal(++index, "Become senior", "medium", new Estimation("2 years", 8)));
        goals.add(new Goal(++index, "Read 10 books", "medium", new Estimation("1 year", 7)));
    }

    @Override
    public List<Goal> getAll() {
        return goals;
    }

    @Override
    public Goal getNewGoal() {
        Goal goal = new Goal();
        goal.setId(++index);
        return goal;
    }

    @Override
    public Goal getById(int id) {
        return goals.stream().filter(goal -> goal.getId() == id).findAny().orElse(null);
    }

    @Override
    public void save(Goal goal) {
        goals.add(goal);
    }

    @Override
    public void deleteById(int id) {
        goals.removeIf(goal -> goal.getId() == id);
    }

    @Override
    public void edit(Goal goal) {
        deleteById(goal.getId());
        goals.add(goal);
        goals.sort(Comparator.comparing(Goal::getId));
    }
}
