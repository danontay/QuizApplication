package com.quizapp.controllers;

import com.quizapp.models.Question;
import com.quizapp.services.QuestionService;
import com.quizapp.services.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizapp.models.Student;
import com.quizapp.services.StudentService;

import java.util.List;

@Controller
public class StudentController {

	@Autowired
	private QuestionService questionService;

	@Autowired
	private StudentServiceImpl studentService;


	public StudentController(StudentServiceImpl studentService) {
		super();
		this.studentService = studentService;
	}

	// Only students can register
	@GetMapping("/register/new")
	public String showStudentRegistrationForm(Model model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "register";
	}

	
	@PostMapping("/students")
	public String createStudent(@ModelAttribute("student") Student student) {
		student.setRole("student");
		studentService.saveStudent(student);
		return "redirect:/login/student";
	}


	@GetMapping("/student/dashboard")
	public String showStudentDashboard(Model model) {
		model.addAttribute("student", ContextController.getStudent());
		return "student-dashboard";
	}

	@GetMapping("/student/schedule/list")
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


	@GetMapping("/student/edit/{id}")
	public String showUpdateStudentPage(@PathVariable Long id, Model model) {
		Student student = studentService.getStudentById(id);
		model.addAttribute("student", student);
		return "admin-student-edit";
	}

	@GetMapping("/student/delete/{id}")
	public String deleteStudent(@PathVariable Long id, Model model) {
		studentService.deleteStudentById(id);
		return "redirect:/admin/dashboard";
	}

	@PostMapping("/students/{id}")
	public String updateStudent(@PathVariable Long id,
			@ModelAttribute("student") Student student,
			Model model) {
		Student existingStudent = studentService.getStudentById(id);
		existingStudent.setId(id);
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		existingStudent.setUsername(student.getUsername());
		existingStudent.setPassword(student.getPassword());
		studentService.updateStudent(existingStudent);
		return "redirect:/admin/dashboard";		
	}
	
	

}
