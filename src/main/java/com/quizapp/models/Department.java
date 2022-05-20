package com.quizapp.models;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @Column(name = "department_id")
    private Long id;

    @Column(name = "dep_name")
    private String depName;

    @Column(name = "dep_description")
    private String desc;

    @Column(name = "faculty_id")
    private Long faculty_id;

}
