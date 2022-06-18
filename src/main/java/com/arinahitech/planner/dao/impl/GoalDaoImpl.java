package com.arinahitech.planner.dao.impl;

import com.arinahitech.planner.dao.GoalDao;
import com.arinahitech.planner.model.Estimation;
import com.arinahitech.planner.model.Goal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class GoalDaoImpl implements GoalDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Goal> getAll() {
        List<Goal> goals = new ArrayList<>();
        return jdbcTemplate.query("SELECT * FROM goals LEFT JOIN estimations ON goals.goal_id = estimations.goal_id",
            (resultSet, rowNum) -> {
                Goal goal = new Goal();
                goal.setId(resultSet.getInt("goal_id"));
                goal.setName(resultSet.getString("name"));
                goal.setPriority(resultSet.getString("priority"));
                Estimation estimation = new Estimation();
                estimation.setComplexity(resultSet.getInt("complexity"));
                estimation.setDuration(resultSet.getString("duration"));
                goal.setEstimation(estimation);
                goals.add(goal);
                return goal;
            }
        );
    }

    @Override
    public Goal getById(int id) {
        Goal goal = new Goal();
        // TO DO
        return goal;
    }

    @Override
    public void save(Goal goal) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO goals (name, priority) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, goal.getName());
            statement.setString(2, goal.getPriority());
            return statement;
        }, key);

        int id = (int) Objects.requireNonNull(key.getKeys()).get("goal_id");
        jdbcTemplate.update("INSERT INTO estimations (duration, complexity, goal_id) VALUES (?, ?, ?)",
            goal.getEstimation().getDuration(),
            goal.getEstimation().getComplexity(),
            id
        );
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM goals WHERE goal_id = ?", id);
        jdbcTemplate.update("DELETE FROM estimations WHERE goal_id = ?", id);
    }

    @Override
    public void edit(Goal goal) {
//      // TO DO
    }
}
