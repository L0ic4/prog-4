package com.example.prog4.controller.Data.InputData;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class PhoneNumberInput {
  private List<String> phoneNumbers;
  private List<String> countryCodes;
}
