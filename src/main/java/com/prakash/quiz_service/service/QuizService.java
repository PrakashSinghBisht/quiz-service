package com.prakash.quiz_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.prakash.quiz_service.Feign.QuizInterface;
import com.prakash.quiz_service.entity.QuestionWrapper;
import com.prakash.quiz_service.entity.Quiz;
import com.prakash.quiz_service.model.QuizAns;
//import com.prakash.quiz_service.repository.QuestionRepository;
import com.prakash.quiz_service.repository.QuizRepository;

@Service
public class QuizService {
	@Autowired
	QuizRepository quizRepository;
	
//	@Autowired 
//	QuestionRepository questionRepository;
	
	@Autowired
	QuizInterface quizInterface;
	
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
//		List<Question> questions = questionRepository.findRandomQuestionsByCategory(category,numQ);
//		Quiz quiz = new Quiz();
//		quiz.setTitle(title);
//		quiz.setQuestions(questions);
//		quizRepository.save(quiz);
		
		//List<Integer> questions = //interact with generate api of Question service which is in QuestionController. previously we used to use RestTemplate but there we need to hardcode the IP
		//So we will use OpenFeign
		List<Integer> questionIds = quizInterface.getQuestionsForQuiz(category, numQ).getBody();	//.getBody because it is returning ResponseEntity and we just want ResponseBody (Integer), so we will use getBody()
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questionIds);
		quizRepository.save(quiz);
		
		return new ResponseEntity<String>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
//		Quiz quiz = quizRepository.findById(id).orElse(null);	//can handle Optional using orElse()
//		List<Question> questionsFromDB = quiz.getQuestions();
		//List<QuestionWrapper> questionsForUser = new ArrayList<>();
//		for(Question ques : questionsFromDB) {
//			QuestionWrapper qw = new QuestionWrapper(ques.getId(),ques.getQuestionTitle(),
//					ques.getOption1(),ques.getOption2(),ques.getOption3(),ques.getOption4());
//			questionsForUser.add(qw);
//		}
		//return questionsForUser;
		Quiz quiz = quizRepository.findById(id).get();
		List<Integer> quizIds = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questionsForUser = quizInterface.getQuestionsFromId(quizIds);
		return questionsForUser;
	}

	public ResponseEntity<String> calculateCorrectAns(int id, List<QuizAns> quizAns) {
//		Quiz quiz = quizRepository.findById(id).get();	//can handle Optional using get()
//		List<Question> questionsFromDB = quiz.getQuestions();
		int correctAns = 0;
//		for(int i=0;i<questionsFromDB.size();i++) {
//			if(quizAns.get(i).getResponse().equals(questionsFromDB.get(i).getRightAnswer())) {
//				correctAns++;
//			}
//		}
		ResponseEntity<String> totalScore = quizInterface.getScore(quizAns);
		return totalScore;
	}
}
