package com.team7.question.services;

import com.team7.question.model.Participant;
import com.team7.question.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    @Autowired
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> getAllUsers() {
      return  participantRepository.findAll();
    }

    public Participant addParticipant(Participant participant) {
        return participantRepository.save(participant);
    }


    public Participant getParticipantByEmail(String email) {
        return participantRepository.getParticipantByEmail(email);
    }


}
