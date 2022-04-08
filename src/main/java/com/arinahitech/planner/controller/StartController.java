package com.arinahitech.planner.controller;

import com.arinahitech.planner.model.Estimation;
import com.arinahitech.planner.model.Goal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StartController {

    private List<Goal> goals;
    private static int index = 0;

    {
        goals = new ArrayList<>();
        goals.add(new Goal(++index, "English B2", "high", new Estimation("6 months")));
        goals.add(new Goal(++index, "Move to another country", "high", new Estimation("1 month")));
        goals.add(new Goal(++index, "Become senior", "medium", new Estimation("2 years")));
        goals.add(new Goal(++index, "Read 10 books", "medium", new Estimation("1 year")));
    }

    @GetMapping("/")
    public String getGoals(Model model) {
        model.addAttribute("id", UUID.randomUUID());
        model.addAttribute("goals", goals);
        return "goalsList";
    }

    @GetMapping("/createGoal")
    public String createGoal(Model model) {
        Goal goal = new Goal();
        goal.setId(++index);
        model.addAttribute("goal", goal);
        return "createGoalForm";
    }

    @GetMapping("/editGoal/{id}")
    public String editGoal(@PathVariable("id") int id, Model model) {
        Goal editGoal = goals.stream().filter(goal -> goal.getId() == id).findAny().get();
        model.addAttribute("goal", editGoal);
        return "editGoalForm";
    }

    @PostMapping("/editGoal")
    public String editGoal(Goal editGoal) {
        goals.removeIf(goal -> goal.getId() == editGoal.getId());
        goals.add(editGoal);
        goals.sort(Comparator.comparing(Goal::getId));
        return "redirect:/";
    }

    @PostMapping("/addGoal")
    public String addGoal(Goal goal) {
        goals.add(goal);
        return "redirect:/";
    }

    @GetMapping("/deleteGoal/{id}")
    public String deleteGoal(@PathVariable("id") int id) {
        goals.removeIf(goal -> goal.getId() == id);
        return "redirect:/";
    }

}
