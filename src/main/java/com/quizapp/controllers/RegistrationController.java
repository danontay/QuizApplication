package com.quizapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizapp.dto.UserDTO;
import com.quizapp.models.Admin;
import com.quizapp.models.Student;
import com.quizapp.models.Teacher;
import com.quizapp.services.AdminService;
import com.quizapp.services.StudentService;
import com.quizapp.services.TeacherService;

@Controller
public class RegistrationController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private TeacherService teacherService;

	@PostMapping("/register")
	public String registerNewUser(@ModelAttribute UserDTO userDto) {
		if (userDto.getUser_role().equalsIgnoreCase("student")) {
			Student student = new Student();
			student.setFirstName(userDto.getFirstName());
			student.setLastName(userDto.getLastName());
			student.setUsername(userDto.getUsername());
			student.setPassword(userDto.getPassword());
			studentService.saveStudent(student);
		} else if (userDto.getUser_role().equalsIgnoreCase("admin")) {
			Admin admin = new Admin();
			admin.setFirstName(userDto.getFirstName());
			admin.setLastName(userDto.getLastName());
			admin.setUsername(userDto.getUsername());
			admin.setPassword(userDto.getPassword());
			adminService.saveAdmin(admin);
		} else if (userDto.getUser_role().equalsIgnoreCase("teacher")) {
			Teacher teacher = new Teacher();
			teacher.setFirstName(userDto.getFirstName());
			teacher.setLastName(userDto.getLastName());
			teacher.setUsername(userDto.getUsername());
			teacher.setPassword(userDto.getPassword());
			teacherService.saveTeacher(teacher);
		}
		return "redirect:/admin/dashboard";
	}

}
