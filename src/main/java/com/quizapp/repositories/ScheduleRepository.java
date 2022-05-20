package com.quizapp.repositories;

import com.quizapp.dto.ScheduleDTO;
import com.quizapp.dto.ScheuduleDTO1;
import com.quizapp.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query(value = "SELECT new com.quizapp.dto.ScheduleDTO(s.subjectName, sch.date, sch.room, g.groupName, t.firstName, t.lastName) from Schedule sch " +
            " join Subject s on s.subjectId= sch.subjectId " +
            "join sch.group g " +
            "join Teacher t ON t.Id = sch.teacherId " +
            "where sch.teacherId = :id" )
    List<ScheduleDTO> findAllSchedule(Long id);

    @Procedure("schedule_info")
    List<ScheuduleDTO1> getSchedule();

    List<Schedule> findAll();
}
