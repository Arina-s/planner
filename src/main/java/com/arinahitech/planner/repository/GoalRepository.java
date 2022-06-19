package com.arinahitech.planner.repository;

import com.arinahitech.planner.model.Goal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {

    Goal findByPriority(String priority);

    List<Goal> findAll();

    Goal findById(int id);

    void deleteById(int id);

    Goal save(Goal goal);

}
