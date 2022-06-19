package com.arinahitech.planner.controller;

import com.arinahitech.planner.model.Goal;
import com.arinahitech.planner.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GoalController {

    private static String redirect = "redirect:/";

    @Autowired
    private GoalService goalService;

    @GetMapping("/")
    public String getGoals(Model model) {
        model.addAttribute("goals", goalService.getAll());
        return "goalsList";
    }

    @GetMapping("/createGoal")
    public String createGoal(Model model) {
        model.addAttribute("goal", new Goal());
        return "createGoalForm";
    }

    @GetMapping("/editGoal/{id}")
    public String editGoal(@PathVariable("id") int id, Model model) {
        model.addAttribute("goal", goalService.getById(id));
        return "editGoalForm";
    }

    @PostMapping("/editGoal")
    public String editGoal(Goal editGoal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editGoalForm";
        }
        goalService.edit(editGoal);
        return redirect;
    }

    @PostMapping("/addGoal")
    public String addGoal(Goal goal, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "createGoalForm";
        }
        goalService.save(goal);
        return redirect;
    }

    @GetMapping("/deleteGoal/{id}")
    public String deleteGoal(@PathVariable("id") int id) {
        goalService.deleteById(id);
        return redirect;
    }

    // @RestController = @Controller + @ResponseBody
    // Param не может быть с @GetMapping
    @ResponseBody
    @PostMapping("/find")
    public Goal getByPriority(@Param("priority") String priority) {
        return goalService.getByPriority(priority);
    }

}
