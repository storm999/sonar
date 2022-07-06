package com.team7.question.repository;

import com.team7.question.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

  //  List<Question> getByRoomIdOrderByVotedUsersAsc(String roomId);


    List<Question> getAllByRoomId(int id);

    List<Question> getAllByRoomIdOrderByVotedParticipantsAsc(int roomId);

}
