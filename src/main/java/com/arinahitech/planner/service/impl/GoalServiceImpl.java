package com.arinahitech.planner.service.impl;

import com.arinahitech.planner.dao.GoalDao;
import com.arinahitech.planner.exception.GoalException;
import com.arinahitech.planner.model.Goal;
import com.arinahitech.planner.service.GoalService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalDao goalDao;

    @Override
    public List<Goal> getAll() {
        return goalDao.getAll();
    }

    @Override
    public Goal getById(int id) {
        return goalDao.getById(id);
    }

    @Override
    public void save(Goal goal) {
        if (!checkGoalExists(goal.getName())) {
            throw new GoalException("The goal with name '" + goal.getName() + "' already exists.");
        } else {
            goalDao.save(goal);
        }
    }

    @Override
    public void deleteById(int id) {
        goalDao.deleteById(id);
    }

    @Override
    public void edit(Goal goal) {
        goalDao.edit(goal);
    }

    private boolean checkGoalExists(String name) {
        List<Goal> goalList = getAll().stream()
            .filter(goal -> goal.getName().equals(name))
            .collect(Collectors.toList());
        return goalList.isEmpty();
    }
}
