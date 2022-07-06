package com.team7.question.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "messages")
public class WsMessage  {

    @Id
    @GeneratedValue
    private int id;

    private String question;


    @ManyToOne()
    @JoinColumn(name = "participant_id",nullable = true)
    private Participant participant;
    private String roomId;
    private Status status;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "participants_likes",
            joinColumns =  @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private List<Participant> votedUsers;
    private String feedback;
    private boolean isAnswered;
    private boolean isVoted;


}
