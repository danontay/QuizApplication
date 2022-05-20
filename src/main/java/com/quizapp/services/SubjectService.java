package com.quizapp.services;

import com.quizapp.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubjectService {
    List<Subject> findAll();
}
