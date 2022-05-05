package com.quizapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.quizapp.dto.ScheduleDTO;
import com.quizapp.dto.ScheuduleDTO1;
import com.quizapp.models.*;
import com.quizapp.repositories.ScheduleRepository;
import com.quizapp.services.*;
import org.aspectj.weaver.loadtime.Options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizapp.dto.QuestionsDTO;
import com.quizapp.repositories.OptionRepository;

@Controller
public class TeacherController {
	public static List<Long> subjectIds;

	private TeacherServiceImpl teacherService;

	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private SessionServiceImpl sessionService;

	@Autowired
	private QuestionServiceImpl questionService;
	
	@Autowired
	private OptionRepository optionRepository;

	public TeacherController(TeacherServiceImpl teacherService) {
		super();
		this.teacherService = teacherService;
	}

	@GetMapping("/teacher/dashboard")
	public String showTeacherDashboard(Model model) {
		List<Session> sessions = new ArrayList<>();
		for (Session session : sessionService.getAllSessions()) {
			if (session.getEnd_time() != null) {
				sessions.add(session);
			}
		}

		model.addAttribute("allSessions", sessions);
		model.addAttribute("teacher", teacherService.getTeacherById(ContextController.getTeacher().getId()));
		return "teacher-dashboard";
	}

	@GetMapping("/teacher/schedule/list")
	public String showAllSchedulePage(Model model) {
		System.out.println("qwert");
		List<ScheduleDTO> schedules = scheduleRepository.findAllSchedule();
//		List<Schedule> schedules = scheduleRepository.findAll();
		System.out.println(schedules.size());

		model.addAttribute("schedules", schedules);
		model.addAttribute("teacher", teacherService.getTeacherById(ContextController.getTeacher().getId()));

		return "teacher-schedule";
	}

	@GetMapping("/teacher/edit/{id}")
	public String showTeacherEditPage(@PathVariable Long id, Model model) {
		Teacher teacher = teacherService.getTeacherById(id);
		model.addAttribute("teacher", teacher);
		return "admin-teacher-edit";
	}

	@PostMapping("/teacher/edit/{id}")
	public String updateAdminDetails(@PathVariable Long id, @ModelAttribute("admin") Admin admin, Model model) {
		Teacher existingTeacher = teacherService.getTeacherById(id);
		existingTeacher.setFirstName(admin.getFirstName());
		existingTeacher.setLastName(admin.getLastName());
		existingTeacher.setUsername(admin.getUsername());
		existingTeacher.setPassword(admin.getPassword());
		teacherService.saveTeacher(existingTeacher);
		return "redirect:/admin/dashboard";
	}

	@GetMapping("/teacher/delete/{id}")
	public String deleteTeacherDetails(@PathVariable Long id, Model model) {
		teacherService.deleteTeacherById(id);
		return "redirect:/admin/dashboard";
	}

	@GetMapping("/teacher/question")
	public String showAddQuestionForm(Model model) {
		QuestionsDTO questionsDTO = new QuestionsDTO();
		model.addAttribute("questionsDto", questionsDTO);
		model.addAttribute("teacher", teacherService.getTeacherById(ContextController.getTeacher().getId()));
		return "teacher-add-question";
	}

	@PostMapping("/teacher/question/add")
	public String addQuestion(@ModelAttribute QuestionsDTO questionsDto, Model model) {
		
		Question question = new Question();
		question.setTitle(questionsDto.getQuestionTitle());
		question.setSubject(questionsDto.getSubject());

		switch (questionsDto.getCorrectAnswer()) {
			case "1":
				question.setOptionCorrect(questionsDto.getOptionOne());
				break;
			case "2":
				question.setOptionCorrect(questionsDto.getOptionTwo());
				break;
			case "3":
				question.setOptionCorrect(questionsDto.getOptionThree());
				break;
			case "4":
				question.setOptionCorrect(questionsDto.getOptionFour());
				break;
		}
		question = questionService.saveQuestion(question);

		List<Option> options = new ArrayList<>();
		Option optionOne = new Option(questionsDto.getOptionOne());
		Option optionTwo = new Option(questionsDto.getOptionTwo());
		Option optionThree = new Option(questionsDto.getOptionThree());
		Option optionFour = new Option(questionsDto.getOptionFour());
		options.add(optionOne);
		options.add(optionTwo);
		options.add(optionThree);
		options.add(optionFour);

		for(Option option: options) {
			option.setQuestion(question);
			optionRepository.save(option);
		}

		return "redirect:/teacher/questions/list";
	}

	@GetMapping("/teacher/question/{id}")
	public String showModifyQuestionForm(@PathVariable Long id, Model model) {
		Question existingQuestion = questionService.findQuestionByquestionId(id);
		QuestionsDTO questionsDto = new QuestionsDTO();
		questionsDto.setQuestionTitle(existingQuestion.getTitle());
		List<Option> options = existingQuestion.getOptions();
		questionsDto.setOptionOne(options.get(0).getOptionText());
		questionsDto.setOptionTwo(options.get(1).getOptionText());
		questionsDto.setOptionThree(options.get(2).getOptionText());
		questionsDto.setOptionFour(options.get(3).getOptionText());
		questionsDto.setCorrectAnswer(existingQuestion.getOptionCorrect());
		questionsDto.setSubject(existingQuestion.getSubject());
		questionsDto.setQuestionId(existingQuestion.getQuestionId());
		model.addAttribute("questionsDto", questionsDto);
		return "teacher-questions-edit";
	}

	@PostMapping("/teacher/question/{id}")
	public String updateQuestionDetails(@PathVariable Long id, @ModelAttribute QuestionsDTO questionsDto, Model model) {

		Question existingQuestion = questionService.findQuestionByquestionId(id);
		List<Option> existingOptions = existingQuestion.getOptions();
		existingQuestion.setTitle(questionsDto.getQuestionTitle());
		existingQuestion.setSubject(questionsDto.getSubject());
			existingOptions.get(0).setOptionText(questionsDto.getOptionOne());
			existingOptions.get(1).setOptionText(questionsDto.getOptionTwo());
			existingOptions.get(2).setOptionText(questionsDto.getOptionThree());
			existingOptions.get(3).setOptionText(questionsDto.getOptionFour());
		switch (questionsDto.getCorrectAnswer()) {
		case "1":
			existingQuestion.setOptionCorrect(questionsDto.getOptionOne());
			break;
		case "2":
			existingQuestion.setOptionCorrect(questionsDto.getOptionTwo());
			break;
		case "3":
			existingQuestion.setOptionCorrect(questionsDto.getOptionThree());
			break;
		case "4":
			existingQuestion.setOptionCorrect(questionsDto.getOptionFour());
			break;
		}
		
		questionService.saveQuestion(existingQuestion);
		return "redirect:/teacher/questions/list";

	}

	@GetMapping("/teacher/questions/list")
	public String showAllQuestionsPage(Model model) {
		List<Question> scienceQuestions = questionService.getAllQuestionsBySubject("science");
		List<Question> mathsQuestions = questionService.getAllQuestionsBySubject("maths");
		List<Question> physicsQuestions = questionService.getAllQuestionsBySubject("physics");
		List<Question> englishQuestions = questionService.getAllQuestionsBySubject("english");
		model.addAttribute("scienceQuestions", scienceQuestions);
		model.addAttribute("mathsQuestions", mathsQuestions);
		model.addAttribute("englishQuestions", englishQuestions);
		model.addAttribute("physicsQuestions", physicsQuestions);

		return "teachers-questions-list";
	}

	
	@GetMapping("/teacher/question/delete/{id}")
	public String deleteQuestion(@PathVariable Long id, Model model) {
		Question question = questionService.findQuestionByquestionId(id);
		List<Option> options = question.getOptions();
		for(Option option: options) {
			optionRepository.delete(option);
		}
		questionService.deleteQuestionByquestionId(id);
		return "redirect:/teacher/questions/list";
	}

}
