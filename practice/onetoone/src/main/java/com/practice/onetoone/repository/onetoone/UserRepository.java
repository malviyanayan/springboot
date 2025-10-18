package com.practice.onetoone.repository.onetoone;

import com.practice.onetoone.entity.onetoone.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
