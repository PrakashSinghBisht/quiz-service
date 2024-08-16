package com.prakash.quiz_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prakash.quiz_service.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
