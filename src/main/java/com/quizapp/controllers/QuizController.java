package com.quizapp.controllers;

import java.util.Calendar;
import java.util.List;


import com.quizapp.repositories.QuizRepository;
import com.quizapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quizapp.dto.AnswersDTO;
import com.quizapp.models.Question;
import com.quizapp.models.Quiz;
import com.quizapp.models.Session;
import com.quizapp.utils.Utils;

@Controller
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuestionServiceImpl questionService;
	
	@Autowired
	private SessionServiceImpl sessionService;

	@Autowired
	private QuizRepository quizRepository;
	

	public QuizController(QuestionServiceImpl questionService) {
		super();
		this.questionService = questionService;
	}

	@GetMapping("/{subject}")
	public String showQuiz(@PathVariable String subject, Model model) {
		Session session = new Session();
		session.setStart_time(Calendar.getInstance());
		List<Question> questions = questionService.getAllQuestionsBySubject(subject);
		Quiz quiz = quizRepository.findBySubject(subject);
		System.out.println("111111");

//		quiz = quizRepository.saveAndFlush(quiz);

		System.out.println("0000000000000");
		System.out.println(quiz.getQuizId());
		System.out.println("0000000000000-----");
		session.setQuiz(quiz);
		session.setScore(0);
		CurrentUserController.getStudent().setSession(session);
		session.setStudentId(CurrentUserController.getStudent().getId());
		session.getStudent().setUsername(CurrentUserController.getStudent().getUsername());

		CurrentUserController.setSession(session); //optional shared context
		sessionService.saveSession(session);
		CurrentUserController.questions = questions;
		model.addAttribute("questions", questions);
		AnswersDTO answersDto = new AnswersDTO();
		model.addAttribute("answersDto", answersDto);
		model.addAttribute("student", CurrentUserController.getStudent());
		return "quiz";
	}
	
	
	@PostMapping("/{subject}/submit")
	public String validateQuiz(@PathVariable String subject, 
			@ModelAttribute AnswersDTO answersDto,
			Model model) {

		List<Question> questions = CurrentUserController.questions;
		int correct=0;
		int opted=0;
		String[] submittedAnswers = answersDto.getAnswers();

		for(int i=0; i<questions.size(); i++) {
			if(submittedAnswers[i] != null) {
				opted++;
				if(submittedAnswers[i].equalsIgnoreCase(questions.get(i).getOptionCorrect())) {
					correct++;
				}
			}
		}

		double score = Utils.calculateNegativeMarks(opted, correct);

		Session existingSession = sessionService.findSessionById(CurrentUserController.getSession().getId());

		existingSession.setEnd_time(Calendar.getInstance());
		existingSession.setScore(score);

		sessionService.saveSession(existingSession);
		System.out.println(score);
		
		model.addAttribute("score", score);
		model.addAttribute("total", "Out of " + questions.size());
		model.addAttribute("student", CurrentUserController.getStudent());
		return "quiz-results";
	}
	
}
