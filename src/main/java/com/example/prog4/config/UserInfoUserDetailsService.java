//package com.example.prog4.config;
//
//import com.example.prog4.entity.UserEntity;
//import com.example.prog4.repository.EmployeeDb.UserRepository;
//import java.util.Optional;
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//@Component
//@AllArgsConstructor
//public class UserInfoUserDetailsService implements UserDetailsService {
//  private final UserRepository userRepository;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<UserEntity> user = userRepository.findByUsername(username);
//    return user.map(com.example.prog4.config.UserInfoUserDetails::new)
//        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//  }
//}
