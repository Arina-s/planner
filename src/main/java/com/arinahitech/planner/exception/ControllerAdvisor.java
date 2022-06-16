package com.arinahitech.planner.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(GoalException.class)
    public String handle(GoalException exception, Model model) {
        model.addAttribute("exception", exception.getMessage());
        return "errorPage";
    }

}
