package com.team7.question.services;

import com.team7.question.model.User;
import com.team7.question.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
      return  userRepository.findAll();
    }

}
