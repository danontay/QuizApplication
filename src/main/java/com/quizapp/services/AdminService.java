package com.quizapp.services;


import java.util.List;

import com.quizapp.models.Admin;

public interface AdminService {

	List<Admin> getAllAdmins();
	Admin saveAdmin(Admin admin);
	Admin getAdminById(Long Id);
	void deleteAdminById(Long Id);
	
}
