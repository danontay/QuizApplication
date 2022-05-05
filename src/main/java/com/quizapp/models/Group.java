package com.quizapp.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")

public class Group {
    @Id
    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "group_name")
    private String groupName;


}
