package com.team7.question.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Participant {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    @ManyToMany(mappedBy = "participantList")
    @JsonIgnore
    private List<Room> roomList;

    @OneToMany(mappedBy = "participant")
    @JsonIgnore
    private List<Question> questions;

    @JsonIgnore
    @ManyToMany(mappedBy = "votedParticipants")
    private List<Question> votedQuestions;

}
