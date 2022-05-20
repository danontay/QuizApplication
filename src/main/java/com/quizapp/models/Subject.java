package com.quizapp.models;

import javax.persistence.*;

@Entity
@Table(name = "subjects", schema = "public")
public class Subject {
    @Id
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @Column(name = "subject_name")
    public String subjectName;

    @Column(name = "description")
    private String description;

    @Column(name = "credits")
    private String credits;



    public Long getId() {
        return subjectId;
    }

    public void setId(Long id) {
        this.subjectId = id;
    }



}
