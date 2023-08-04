package com.techforb.unicomer.repository;

import com.techforb.unicomer.model.DniType;
import com.techforb.unicomer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByDniAndDniType(String dni, DniType dniType);

    Optional<User> findByEmail(String email);
    boolean existsByDniAndDniType(String dni, DniType dniType);
    boolean existsByEmail(String email);
}
