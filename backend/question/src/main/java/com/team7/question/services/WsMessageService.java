package com.team7.question.services;

import com.team7.question.model.Participant;
import com.team7.question.model.WsMessage;
import com.team7.question.repository.ParticipantRepository;
import com.team7.question.repository.WsMessageRepository;
import com.team7.question.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WsMessageService {

  private WsMessageRepository wsMessageRepository;
  private ParticipantRepository participantRepository;

   @Autowired
    public WsMessageService(WsMessageRepository wsMessageRepository,ParticipantRepository participantRepository) {
       this.participantRepository = participantRepository;
       this.wsMessageRepository = wsMessageRepository;
    }

    public WsMessage addMessage(WsMessage wsMessage) {
        return this.wsMessageRepository.save(wsMessage);
    }


    public List<WsMessage> getAllMessages(String roomId) {
       return wsMessageRepository.getByRoomIdOrderByVotedUsersAsc(roomId);
    }



    public Response addUserToWsMessageLike(int participantId,int wsMessageId) {
        Participant participant =  participantRepository.findById(participantId).get();
        WsMessage wsMessage = wsMessageRepository.findById(wsMessageId).get();

        List<Participant> votedParticipants =  wsMessage.getVotedUsers();
        if(votedParticipants.contains(participant))       {
            votedParticipants.remove(participant);
            wsMessageRepository.save(wsMessage);
            Response response = new Response();
            response.setMessage("unvoted");
            response.setStatus(false);
            return  response;


        }   else {
            votedParticipants.add(participant);
            wsMessageRepository.save(wsMessage);
            Response response = new Response();
            response.setMessage("voted");
            response.setStatus(true);
            return  response;
        }

    }




}
