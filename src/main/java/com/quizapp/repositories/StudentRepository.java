package com.quizapp.repositories;

import com.quizapp.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findStudentByUsername(String username);
	Student getStudentByUsername(String username);
	void deleteById(Long Id);
}
