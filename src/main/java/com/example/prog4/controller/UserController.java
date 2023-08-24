package com.example.prog4.controller;

import com.example.prog4.controller.Data.InputData.UserInput;
import com.example.prog4.controller.Mapper.UserMapper;
import com.example.prog4.entity.Employee.UserEntity;
import com.example.prog4.service.JwtService;
import com.example.prog4.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
  private final UserService userService;
  private final JwtService jwtService;
  private final UserMapper userMapper;
  private final AuthenticationManager authenticationManager;

  @GetMapping("/signup")
  public String signUp(Model model) {
    model.addAttribute("user", new UserEntity());
    return "sign_up";
  }

  @PostMapping("/createuser")
  public String CreateUser(@ModelAttribute("user") UserInput userInput) {
    userService.saveUser(userMapper.toDomain(userInput));
    return "redirect:/login";
  }


  @GetMapping("/login")
  public String login(Model model) {
    model.addAttribute("user", new UserEntity());
    return "login";
  }

  @PostMapping("/connectuser")
  public String connectUser(@ModelAttribute("user") UserEntity userEntity,
                            HttpServletResponse response) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(userEntity.getUsername(),
            userEntity.getPassword()));
    String Token;
    if (authentication.isAuthenticated()) {
      Token = jwtService.generateToken(userEntity.getUsername());
      Cookie cookie = new Cookie("JWT", Token);
      response.addCookie(cookie);
    } else {
      System.out.println("Not authenticated");
    }
    return "redirect:/employees";
  }
}
