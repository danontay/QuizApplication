package com.quizapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subjects", schema = "public")
public class Subject {
    @Id
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @Column(name = "subject_name")
    private String subjectName;

    public Long getId() {
        return subjectId;
    }

    public void setId(Long id) {
        this.subjectId = id;
    }
}
