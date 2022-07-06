package com.team7.question.repository;

import com.team7.question.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant,Integer> {

    Participant getParticipantByEmail(String email);
}
