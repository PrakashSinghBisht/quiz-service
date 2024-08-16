package com.prakash.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prakash.quiz_service.entity.QuestionWrapper;
import com.prakash.quiz_service.entity.QuizDto;
import com.prakash.quiz_service.model.QuizAns;
import com.prakash.quiz_service.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {

	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
		return quizService.createQuiz(quizDto.getCategory(), quizDto.getNumQ(), quizDto.getTitle());
	}
	
	//after creating quiz fetch the questions based on the random IDs while creating quiz
	@GetMapping("getQuestions/{id}")
	public ResponseEntity<List<QuestionWrapper>> getAllQuestions(@PathVariable int id){
		return quizService.getQuizQuestions(id);
	}
	
	@PostMapping("submitQuiz/{id}")
	public ResponseEntity<String> submitQuiz(@PathVariable int id, @RequestBody List<QuizAns> quizAns){
		return quizService.calculateCorrectAns(id, quizAns);
	}
	
}
