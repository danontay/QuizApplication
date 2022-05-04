package com.quizapp.models;

import javax.persistence.*;

@Entity
@Table(name = "options")
public class Option {
  
	@Id
	@Column(name="option_id")
	@GeneratedValue(
			strategy = GenerationType.AUTO,
			generator = "option_generator"
	)
	@SequenceGenerator(
			name = "option_generator",
			sequenceName = "option_seq", allocationSize = 1
	)
	private Long Id;

	
	@Column(name = "option_text")
	private String optionText;
	
	@ManyToOne()
	@JoinColumn(name="question_id")
	private Question question;

	public Option() {
		
	}
	public Option(String optionText) {
		super();
		this.optionText = optionText;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getOptionText() {
		return optionText;
	}

	public void setOptionText(String optionText) {
		this.optionText = optionText;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
