package com.nayan.dofocus.repositories;

import com.nayan.dofocus.entities.Todo;
import com.nayan.dofocus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Ye method Spring Data JPA automatically implement kar dega
    boolean existsByEmail(String email);

    // Find user by email
    Optional<User> findByEmail(String email);

}
