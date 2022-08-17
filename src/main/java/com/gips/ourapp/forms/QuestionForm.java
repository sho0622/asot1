package com.gips.ourapp.forms;

import lombok.Data;

@Data
public class QuestionForm {
	private Integer question_id;
	private String question;
	private String correct;
	private String choice1;
	private String choice2;
	private String choice3;
	private String choice4;
	private String right;
	private String wrong;
	private Object answer;


}
