package com.quizapp.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name ="sessions")
public class Session {
	
	@Id
	@Column(name = "session_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "quiz_id")
	private Quiz quiz;
	
	@Column(name = "start_time")
	private Calendar start_time;
	
	@Column(name = "end_time")
    private Calendar end_time;
	
	@Column(name = "score")
	private double score;
	
	@Column(name="current_student_id")
	private Long studentId;
	
	public Session(){	
	}

	public Session(Student student, Quiz quiz, Calendar start_time, Calendar end_time, int score) {
		super();
		this.student = student;
		this.quiz = quiz;
		this.start_time = start_time;
		this.end_time = end_time;
		this.score = score;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Calendar getStart_time() {
		return start_time;
	}

	public void setStart_time(Calendar start_time) {
		this.start_time = start_time;
	}

	public Calendar getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Calendar end_time) {
		this.end_time = end_time;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	private String studentName;
	
}
