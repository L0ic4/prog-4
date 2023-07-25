package com.example.prog4.repository;

import com.example.prog4.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  Optional<UserEntity> findByUsername(String userName);

}
