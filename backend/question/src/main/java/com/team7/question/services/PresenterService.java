package com.team7.question.services;

import com.team7.question.model.Presenter;
import com.team7.question.repository.PresenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresenterService {

    private final PresenterRepository presenterRepository;

    @Autowired
    public PresenterService(PresenterRepository presenterRepository) {
        this.presenterRepository = presenterRepository;
    }


    public List<Presenter> getAllPresenters() {
       return presenterRepository.findAll();
    }

    public Presenter addPresenter(Presenter presenter) {
        return presenterRepository.save(presenter);
    }
}
