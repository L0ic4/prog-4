package com.example.prog4.controller.Mapper;

import com.example.prog4.controller.Data.InputData.UserInput;
import com.example.prog4.entity.Employee.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapper {

  public UserEntity toDomain(UserInput userInput) {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return UserEntity.builder()
        .username(userInput.getUsername())
        .password(bCryptPasswordEncoder.encode(userInput.getPassword()))
        .build();
  }
}
