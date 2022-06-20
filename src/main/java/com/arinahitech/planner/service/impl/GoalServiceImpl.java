package com.arinahitech.planner.service.impl;

import com.arinahitech.planner.exception.GoalException;
import com.arinahitech.planner.model.Goal;
import com.arinahitech.planner.repository.GoalRepository;
import com.arinahitech.planner.service.GoalService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public List<Goal> getAll() {
        return goalRepository.findAll();
    }

    @Override
    public Goal getById(int id) {
        return goalRepository.findById(id);
    }

    @Override
    public void save(Goal goal) {
        if (!checkGoalExists(goal.getName())) {
            throw new GoalException("The goal with name '" + goal.getName() + "' already exists.");
        } else {
            goalRepository.save(goal);
        }
    }

    @Override
    public void deleteById(int id) {
        goalRepository.deleteById(id);
    }

    @Override
    public void edit(Goal goal) {
        goalRepository.save(goal);
    }

    private boolean checkGoalExists(String name) {
        List<Goal> goalList = getAll().stream()
            .filter(goal -> goal.getName().equals(name))
            .collect(Collectors.toList());
        return goalList.isEmpty();
    }

    @Override
    public Goal getByPriority(String priority) {
        return goalRepository.findByPriority(priority);
    }

    @Override
    public Goal create(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    public void editById(int id, Goal goal) {

    }
}
