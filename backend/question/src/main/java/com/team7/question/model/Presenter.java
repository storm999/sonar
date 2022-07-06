package com.team7.question.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Presenter {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToOne(mappedBy = "presenter")
    @JsonIgnore
    private Room room;

}
