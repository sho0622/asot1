package com.gips.ourapp.forms;

import lombok.Data;

@Data
public class AnswerForm {
	private String[] answer = new String[10];
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answer5;
	private String answer6;
	private String answer7;
	private String answer8;
	private String answer9;
	private String answer10;

	AnswerForm() {
		this.answer[0] = "answer1";
		this.answer[1] = "answer2";
		this.answer[2] = "answer3";
		this.answer[3] = "answer4";
		this.answer[4] = "answer5";
		this.answer[5] = "answer6";
		this.answer[6] = "answer7";
		this.answer[7] = "answer8";
		this.answer[8] = "answer9";
		this.answer[9] = "answer10";
	}
}
