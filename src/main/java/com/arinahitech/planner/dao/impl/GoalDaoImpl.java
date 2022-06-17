package com.arinahitech.planner.dao.impl;

import com.arinahitech.planner.dao.GoalDao;
import com.arinahitech.planner.model.Estimation;
import com.arinahitech.planner.model.Goal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GoalDaoImpl implements GoalDao {

    private static final String SQL_GET_ALL_GOALS =
        "SELECT * FROM goals LEFT JOIN estimation ON goals.goal_id = estimation.goal_id";

    @Autowired
    private Connection connection;

    @Override
    public List<Goal> getAll() {
        List<Goal> goals = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_GOALS)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Goal goal = new Goal();
                goal.setId(resultSet.getInt("goal_id"));
                goal.setName(resultSet.getString("name"));
                goal.setPriority(resultSet.getString("priority"));
                Estimation estimation = new Estimation();
                estimation.setComplexity(resultSet.getInt("complexity"));
                estimation.setDuration(resultSet.getString("duration"));
                goal.setEstimation(estimation);
                goals.add(goal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goals;
    }

    @Override
    public Goal getById(int id) {
        // to do
        return new Goal();
    }

    @Override
    public void save(Goal goal) {
        try (PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO goals (name, priority) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, goal.getName());
            statement.setString(2, goal.getPriority());
            int changesRows = statement.executeUpdate();
            if (changesRows > 0) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    try (PreparedStatement statement1 = connection.prepareStatement(
                        "INSERT INTO estimation (duration, complexity, goal_id) VALUES (?, ?, ?)"
                    )) {
                        statement1.setString(1, goal.getEstimation().getDuration());
                        statement1.setInt(2, goal.getEstimation().getComplexity());
                        statement1.setInt(3, id);
                        statement1.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        // to do
    }

    @Override
    public void edit(Goal goal) {
        // to do
    }
}
