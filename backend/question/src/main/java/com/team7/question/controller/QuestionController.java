package com.team7.question.controller;

import com.team7.question.response.Response;
import com.team7.question.model.Question;
import com.team7.question.services.ParticipantService;
import com.team7.question.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/questions")
@CrossOrigin
public class QuestionController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    public QuestionService questionService;
    public ParticipantService participantService;

    @Autowired
    public QuestionController(QuestionService questionService, ParticipantService participantService) {
        this.questionService = questionService;
        this.participantService = participantService;
    }


    @PostMapping("/addMessage/{roomId}")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question,@PathVariable  int roomId) {
        return ResponseEntity.ok(questionService.addQuestionToRoom(question,roomId));
    }

    @GetMapping("/get-questions-by-room-id/{roomId}")
    public ResponseEntity<List<Question>> getAllQuestionsByRoomId(@PathVariable int roomId) {
        return ResponseEntity.ok(questionService.getQuestionsByRoomId(roomId));
    }

    @GetMapping("/vote-question/{participantId}/{questionId}")
    public ResponseEntity<Response> voteQuestion(@PathVariable int participantId, @PathVariable int questionId) {
       return ResponseEntity.ok(this.questionService.voteQuestion(participantId,questionId));
    }

    @GetMapping("/set-answer/{questionId}")
    public ResponseEntity<Response> setAnswer(@PathVariable int questionId) {
        return ResponseEntity.ok(this.questionService.setAnswer(questionId));
    }

    @GetMapping("/questions-sorted-vote/{roomId}")
    public ResponseEntity<List<Question>> questionsByVoted(@PathVariable int roomId) {
        return ResponseEntity.ok(this.questionService.getAllQuestionsByVote(roomId));
    }








}
