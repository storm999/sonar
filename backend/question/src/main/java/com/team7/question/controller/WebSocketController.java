package com.team7.question.controller;

import com.team7.question.model.Participant;
import com.team7.question.model.Status;
import com.team7.question.model.WsMessage;
import com.team7.question.response.Response;
import com.team7.question.services.ParticipantService;
import com.team7.question.services.WsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/questions")
@CrossOrigin
public class WebSocketController {
    @Autowired
    private  SimpMessagingTemplate messagingTemplate;
    public WsMessageService wsMessageService;
    public ParticipantService participantService;

    @Autowired
    public WebSocketController(WsMessageService wsMessageService,ParticipantService participantService) {
        this.wsMessageService = wsMessageService;
        this.participantService = participantService;
    }
    @GetMapping("/get-all-messages/{roomId}")
    public ResponseEntity<List<WsMessage>> getAllMessages(@PathVariable String roomId) {
      return ResponseEntity.ok(this.wsMessageService.getAllMessages(roomId));
    }
    @PostMapping("/addMessage")
    public ResponseEntity<WsMessage> addMessage(@RequestBody WsMessage wsMessage) {
        return ResponseEntity.ok(wsMessageService.addMessage(wsMessage));
    }

    @GetMapping("/addVote/{participantId}/{questionId}")
    public ResponseEntity<Object> addVote(@PathVariable int participantId, @PathVariable int questionId) {
        return ResponseEntity.ok(wsMessageService.addUserToWsMessageLike(participantId,questionId));
    }

    @MessageMapping("/private-message")
    public WsMessage receivePrivateMessage(@Payload WsMessage wsMessage) {
        if(wsMessage.getStatus() == Status.JOIN) {
            participantService.addParticipant(wsMessage.getParticipant());
            messagingTemplate.convertAndSend("/roomId/"+wsMessage.getRoomId(),wsMessage);
        }

        if(wsMessage.getStatus() == Status.MESSAGE) {
            Participant participant =  participantService.getParticipantByEmail(wsMessage.getParticipant().getEmail());
          wsMessage.setParticipant(participant);
          wsMessageService.addMessage(wsMessage);
          messagingTemplate.convertAndSend("/roomId/"+wsMessage.getRoomId(),wsMessage);
        }
      //  wsMessageService.addMessage(wsMessage);
      //  messagingTemplate.convertAndSendToUser(wsMessage.getSender(),"/private",wsMessage);
        return wsMessage;
    }





}
