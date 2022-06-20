package com.arinahitech.planner.controller;

import static com.arinahitech.planner.constants.ApiConstants.GOAL_PATH;

import com.arinahitech.planner.model.Goal;
import com.arinahitech.planner.service.GoalService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoalController {

    @Autowired
    private GoalService goalService;

    @GetMapping(GOAL_PATH)
    public List<Goal> getGoals() {
        return goalService.getAll();
    }

    @PostMapping(GOAL_PATH)
    public Goal createGoal(@RequestBody Goal goal) {
        return goalService.create(goal);
    }

    @DeleteMapping(GOAL_PATH + "/{id}")
    public void delete(@PathVariable("id") int id) {
        goalService.deleteById(id);
    }

    @PatchMapping(GOAL_PATH)
    public void editGoal(@RequestBody Goal goal, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            goalService.edit(goal);
        }
    }

}
