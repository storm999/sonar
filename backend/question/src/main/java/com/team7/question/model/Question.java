package com.team7.question.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Question {

    @Id
    @GeneratedValue
    private int id;

    private String question;


    @ManyToOne
    @JoinColumn(name = "participant_1")
    private Participant participant;

    @ManyToOne
    private Room room;

    @ManyToMany
    @JoinColumn(name = "voted_participants")
    private List<Participant> votedParticipants;

    private Status status;

    private String feedback;

    private boolean isAnswered;



}
