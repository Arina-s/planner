package com.arinahitech.planner.dao.impl;

import com.arinahitech.planner.dao.GoalDao;
import com.arinahitech.planner.model.Estimation;
import com.arinahitech.planner.model.Goal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class GoalDaoImpl implements GoalDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Goal> getAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Goal", Goal.class).list();
    }

    @Override
    public Goal getById(int id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM goals LEFT JOIN estimations ON goals.goal_id = estimations.goal_id WHERE goals.goal_id = ?",
            (resultSet, rowNum) -> {
                Goal goal = new Goal();
                goal.setId(resultSet.getInt("goal_id"));
                goal.setName(resultSet.getString("name"));
                goal.setPriority(resultSet.getString("priority"));
                Estimation estimation = new Estimation();
                estimation.setComplexity(resultSet.getInt("complexity"));
                estimation.setDuration(resultSet.getString("duration"));
                goal.setEstimation(estimation);
                return goal;
            },
            new Object[]{id}
        );
    }

    @Override
    public void save(Goal goal) {
        Session session = sessionFactory.getCurrentSession();
        session.save(goal);
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM goals WHERE goal_id = ?", id);
        jdbcTemplate.update("DELETE FROM estimations WHERE goal_id = ?", id);
    }

    @Override
    public void edit(Goal goal) {
        jdbcTemplate.update("UPDATE goals SET name = ?, priority = ? WHERE goal_id = ?",
            goal.getName(),
            goal.getPriority(),
            goal.getId()
        );
        jdbcTemplate.update("UPDATE estimations SET duration = ?, complexity = ? WHERE goal_id = ?",
            goal.getEstimation().getDuration(),
            goal.getEstimation().getComplexity(),
            goal.getId()
        );
    }
}
