package com.arinahitech.planner.repository;

import com.arinahitech.planner.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
