package com.quizapp.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public class Question{
	
	@Id
	@Column(name = "question_id")
	@GeneratedValue(
			strategy = GenerationType.AUTO,
			generator = "question_generator"
	)
	@SequenceGenerator(
			name = "question_generator",
			sequenceName = "question_seq", allocationSize = 1
	)
	private Long questionId;
	
	@Column(name = "question_title", nullable = false)
	private String title;

	@OneToMany(mappedBy="question", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Option> options; //value to text
	
	
	@Column(name = "option_correct", nullable = false)
	private String optionCorrect;
	
	@ManyToOne()
	@JoinColumn(name="quiz_id")
	private Quiz quiz;
	
	@Column(name="subject")
	private String subject;

	@Column(name = "option_chosen")
	private String optionChosen;
	
	
	public Question() {
	}
	
	public Question(String title, List<Option> options, String optionCorrect, Quiz quiz, String subject) {
		super();
		this.title = title;
		this.options = options;
		this.optionCorrect = optionCorrect;
		this.quiz = quiz;
		this.subject = subject;
	}



	public Long getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Long id) {
		this.questionId = questionId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public String getOptionCorrect() {
		return optionCorrect;
	}

	public void setOptionCorrect(String optionCorrect) {
		this.optionCorrect = optionCorrect;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	
}
