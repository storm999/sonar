package com.team7.question.repository;

import com.team7.question.model.Presenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenterRepository extends JpaRepository<Presenter,Integer> {
}
