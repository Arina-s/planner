package com.arinahitech.planner.service.impl;

import com.arinahitech.planner.dao.GoalDao;
import com.arinahitech.planner.model.Goal;
import com.arinahitech.planner.service.GoalService;
import java.util.List;
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
    public Goal getNewGoal() {
        return goalDao.getNewGoal();
    }

    @Override
    public Goal getById(int id) {
        return goalDao.getById(id);
    }

    @Override
    public void save(Goal goal) {
        goalDao.save(goal);
    }

    @Override
    public void deleteById(int id) {
        goalDao.deleteById(id);
    }

    @Override
    public void edit(Goal goal) {
        goalDao.edit(goal);
    }
}
