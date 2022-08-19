package com.gips.ourapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "m_question")
@Data

public class QuestionEntity {
	@Id
	@Column(name = "question_id")
	private int id;
	@Column(name = "question")
	private String question;
	@Column(name = "correct")
	private String correct;
	@Column(name = "choice1")
	private String choice1;
	@Column(name = "choice2")
	private String choice2;
	@Column(name = "choice3")
	private String choice3;
	@Column(name = "choice4")
	private String choice4;
}
