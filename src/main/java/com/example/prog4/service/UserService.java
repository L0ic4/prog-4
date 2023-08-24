package com.example.prog4.service;

import com.example.prog4.entity.Employee.UserEntity;
import com.example.prog4.repository.Employee.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public void saveUser(UserEntity user) {
    userRepository.save(user);
  }

}
