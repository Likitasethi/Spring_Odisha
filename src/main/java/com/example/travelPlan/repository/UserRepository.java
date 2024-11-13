package com.example.travelPlan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.travelPlan.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);
 
}
