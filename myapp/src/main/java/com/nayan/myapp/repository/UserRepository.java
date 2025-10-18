package com.nayan.myapp.repository;

import com.nayan.myapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // custom queries if needed
}

