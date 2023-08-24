package com.example.prog4.controller.Data.InputData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class UserInput {
  private String username;
  private String password;
}
