package com.team7.question.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    private Presenter presenter;


    @OneToMany(mappedBy = "room")
    @JsonIgnore
    private List<Question> questions;


    @ManyToMany
    @JsonIgnore
    private List<Participant> participantList;

}
