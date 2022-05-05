package com.quizapp.models;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "schedule", schema = "public")
public class Schedule {

    @Id
    @Column(name = "schedule_id")
    private Long scheduleId;
    
    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "date")
    private Calendar date;

    @Column(name = "room")
    private String room;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private List<Group> group;


    @Column(name = "teacher_id")
    private Long teacherId;

    @Column(name = "type")
    private String type;
}
