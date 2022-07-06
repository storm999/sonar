package com.team7.question.services;

import com.team7.question.response.Response;
import com.team7.question.model.Participant;
import com.team7.question.model.Question;
import com.team7.question.model.Room;
import com.team7.question.repository.ParticipantRepository;
import com.team7.question.repository.QuestionRepository;

import com.team7.question.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

  private final QuestionRepository questionRepository;
  private final ParticipantRepository participantRepository;

  private final RoomRepository roomRepository;

   @Autowired
    public QuestionService(QuestionRepository questionRepository, ParticipantRepository participantRepository,RoomRepository roomRepository) {
       this.participantRepository = participantRepository;
       this.questionRepository = questionRepository;
       this.roomRepository = roomRepository;
    }

    public Question addQuestionToRoom(Question question,int roomId) {
        Room room = roomRepository.findById(roomId).get();
        question.setRoom(room);
        return this.questionRepository.save(question);
    }

    public List<Question> getQuestionsByRoomId(int roomId) {
       return this.questionRepository.getAllByRoomId(roomId);
    }

    public Response voteQuestion(int participantId, int questionId) {
      Participant participant = participantRepository.findById(participantId).get();
      Question question = questionRepository.findById(questionId).get();
      List<Participant> votedParticipants = question.getVotedParticipants();

      if(votedParticipants.contains(participant)) {
        votedParticipants.remove(participant);
        question.setVotedParticipants(votedParticipants);
        questionRepository.save(question);
          return new Response("unvoted",false);
      } else {
          votedParticipants.add(participant);
          question.setVotedParticipants(votedParticipants);
          questionRepository.save(question);
          return new Response("voted",true);
      }

    }

    public Response setAnswer(int questionId) {
       Question question = questionRepository.findById(questionId).get();

       if(question.isAnswered()) {
           question.setAnswered(false);
           questionRepository.save(question);
           return new Response("unanswered",false);
       } else {
           question.setAnswered(true);
           questionRepository.save(question);
           return new Response("answered",true);
       }

    }

    public List<Question> getAllQuestionsByVote(int roomId) {
     return  this.questionRepository.getAllByRoomIdOrderByVotedParticipantsAsc(roomId);
    }

  //  public List<Question> getAllMessages(String roomId) {
     //  return wsMessageRepository.getByRoomIdOrderByVotedUsersAsc(roomId);
  //  }



}
