package com.nayan.dofocus.repositories;

import com.nayan.dofocus.entities.Todo;
import com.nayan.dofocus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Find all todos for a given user
    List<Todo> findByUser(User user);

    Optional<Todo> findByIdAndUserId(Long todoId, Long userId);
}
