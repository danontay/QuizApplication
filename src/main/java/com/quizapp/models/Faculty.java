package com.quizapp.models;

import javax.persistence.*;

@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    private Long id;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "faculty_description")
    private String description;


}
