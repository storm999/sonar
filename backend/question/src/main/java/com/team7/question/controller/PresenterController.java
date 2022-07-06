package com.team7.question.controller;

import com.team7.question.model.Presenter;
import com.team7.question.services.PresenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/presenters")
public class PresenterController {

    private PresenterService presenterService;

    @Autowired
    public PresenterController(PresenterService presenterService) {
        this.presenterService = presenterService;
    }

    @GetMapping("/get-all-presenters")
    public ResponseEntity<List<Presenter>> getAllPresenters() {
        return ResponseEntity.ok(presenterService.getAllPresenters());
    }

    @PostMapping("/add-presenter")
    public ResponseEntity<Presenter> addPresenter(@RequestBody Presenter presenter) {
        return ResponseEntity.ok(presenterService.addPresenter(presenter));
    }
}
