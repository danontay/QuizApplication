package com.quizapp.controllers;

import java.util.List;

import com.quizapp.models.Department;
import com.quizapp.repositories.DepartmentRepository;
import com.quizapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.quizapp.dto.UserDTO;
import com.quizapp.models.Admin;
import com.quizapp.models.Student;
import com.quizapp.models.Teacher;

@Controller
public class AdminController {

	@Autowired
	private StudentServiceImpl studentService;

	@Autowired
	private AdminServiceImpl adminService;
	
	@Autowired
	private TeacherServiceImpl teacherService;

	@Autowired
	DepartmentRepository departmentRepository;

	@GetMapping("/admin/dashboard")
	public String showAdminDashboard(Model model) {
		List<Student> students = studentService.getAllStudents();
		List<Admin> admins = adminService.getAllAdmins();
		List<Teacher> teachers = teacherService.getAllTeachers();
		model.addAttribute("admin", CurrentUserController.getAdmin());
		model.addAttribute("students", students);
		model.addAttribute("admins", admins);
		model.addAttribute("teachers", teachers);
		return "admin-dashboard";
	}

	@GetMapping("/admin/users")
	public String showAddUsersPage(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("admin", CurrentUserController.getAdmin());
		model.addAttribute("userDto", userDTO);
		return "admin-add-user";
	}

	@GetMapping("/admin/departments")
	public String showDepatmentsPage(Model model) {
		UserDTO userDTO = new UserDTO();
		List<Department> departments = departmentRepository.findAll();
//		List<Student> students = studentService.getAllStudents();
//		List<Admin> admins = adminService.getAllAdmins();
//		List<Teacher> teachers = teacherService.getAllTeachers();
		model.addAttribute("admin", CurrentUserController.getAdmin());
		model.addAttribute("departments", departments);
//		model.addAttribute("admins", admins);
//		model.addAttribute("teachers", teachers);

		model.addAttribute("admin", CurrentUserController.getAdmin());
		model.addAttribute("userDto", userDTO);
		return "admin-department-list";
	}

	@GetMapping("/admin/faculties")
	public String showFacultiesPage(Model model) {
		UserDTO userDTO = new UserDTO();
		model.addAttribute("admin", CurrentUserController.getAdmin());
		model.addAttribute("userDto", userDTO);
		return "admin-faculties-list";
	}
	
	@GetMapping("/admin/edit/{id}")
	public String showAdminEditPage(@PathVariable Long id, Model model) {
		Admin admin = adminService.getAdminById(id);
		model.addAttribute("admin", admin);
		return "admin-admin-edit";
	}

	@PostMapping("/admin/edit/{id}")
	public String updateAdminDetails(@PathVariable Long id, @ModelAttribute("admin") Admin admin,
			Model model) {
		Admin existingAdmin = adminService.getAdminById(id);
		existingAdmin.setFirstName(admin.getFirstName());
		existingAdmin.setLastName(admin.getLastName());
		existingAdmin.setUsername(admin.getUsername());
		existingAdmin.setPassword(admin.getPassword());
		adminService.saveAdmin(existingAdmin);
		return "redirect:/admin/dashboard";
	}
	
	@GetMapping("/admin/delete/{id}")
	public String deleteAdminDetails(@PathVariable Long id,
			Model model) {
		List<Admin> admins = adminService.getAllAdmins();
		if(admins.size() == 1) {
			return "redirect:/admin/dashboard?adminerror";
		}
		adminService.deleteAdminById(id);
		return "redirect:/admin/dashboard";
	}
}
