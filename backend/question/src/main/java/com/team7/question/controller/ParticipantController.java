package com.team7.question.controller;

import com.team7.question.model.Participant;

import com.team7.question.services.ParticipantService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/participants")
public class ParticipantController {


    private ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    @GetMapping("get-all-participants")
    public ResponseEntity<List<Participant>> getAllUsers() {
        return ResponseEntity.ok(participantService.getAllUsers());

    }

    @PostMapping("/add-participant")
    public ResponseEntity<Participant> addParticipant( @RequestBody Participant participant) {
      return ResponseEntity.ok( participantService.addParticipant(participant));
    }

}
