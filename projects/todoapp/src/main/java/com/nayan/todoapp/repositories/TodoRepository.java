package com.nayan.todoapp.repositories;

import com.nayan.todoapp.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByOwner_Username(String username);
    Optional<Todo> findByIdAndOwner_Username(Long id, String username);
}

