package com.prakash.quiz_service.Feign;

import java.util.List;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.prakash.quiz_service.entity.QuestionWrapper;
import com.prakash.quiz_service.model.QuizAns;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	@GetMapping("question/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String category,
			@RequestParam int noOfQuestions);
	
	@PostMapping("question/getQuestionsById")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
	
	@PostMapping("question/getScore")
	public ResponseEntity<String> getScore(@RequestBody List<QuizAns> quizAnswers);
}
