package com.example.prog4.config.security;

import com.example.prog4.entity.Employee.UserEntity;
import com.example.prog4.repository.Employee.UserRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserInfoUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<UserEntity> user = userRepository.findByUsername(username);
    return user.map(UserInfoUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
