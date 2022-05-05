package com.quizapp.dto;

import java.util.Calendar;


public class ScheduleDTO {
    private String subjectName;
    private Calendar date;
    private String room;
    private String groupName;
    private String firstName;
    private String lastName;

    public ScheduleDTO() {
    }

    public ScheduleDTO(String subjectName, Calendar date, String room, String groupName, String firstName, String lastName) {
        this.subjectName = subjectName;
        this.date = date;
        this.room = room;
        this.groupName = groupName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
