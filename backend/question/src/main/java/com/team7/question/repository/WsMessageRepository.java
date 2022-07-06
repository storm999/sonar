package com.team7.question.repository;

import com.team7.question.model.Participant;
import com.team7.question.model.WsMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WsMessageRepository extends JpaRepository<WsMessage,Integer> {

    List<WsMessage> getByRoomIdOrderByVotedUsersAsc(String roomId);





}
