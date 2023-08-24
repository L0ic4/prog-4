package com.example.prog4.service;

import com.example.prog4.entity.Employee.UserEntity;
import com.example.prog4.repository.Employee.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public UserEntity getUserById(int id) {
    return userRepository.findById(id).orElse(null);
  }

  public void saveUser(UserEntity user) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  public UserEntity updateUser(UserEntity user) {
    return userRepository.save(user);
  }

  public void deleteUser(int id) {
    userRepository.deleteById(id);
  }
}
