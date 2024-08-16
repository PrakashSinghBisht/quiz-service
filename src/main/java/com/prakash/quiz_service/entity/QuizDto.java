package com.prakash.quiz_service.entity;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

@Data
public class QuizDto {

	private String category; 
	private int numQ;
	private String title;
	
}
