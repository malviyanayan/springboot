package com.nayan.dofocus.services;

import com.nayan.dofocus.entities.Todo;
import com.nayan.dofocus.entities.User;
import com.nayan.dofocus.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private HttpSession session;

    // Create a new todo
    public void saveNewTodo(Todo todo) {
        todoRepository.save(todo);
    }

    public Todo findTodo(Long todoId, Long userId){
        return todoRepository.findByIdAndUserId(todoId, userId).orElse(null);
    }

    // Get all todos for logged-in user
    public List<Todo> getTodos() {
        User user = (User) session.getAttribute("user");
        if(user == null) return List.of(); // Empty list if not logged in

        return todoRepository.findByUser(user);
    }

    // Update todo status
    public Todo updateTodoStatus(Todo todo, Boolean isDone) {
        todo.setIsDone(isDone);
        return todoRepository.save(todo);
    }

    // Delete todo
    public boolean deleteTodo(Long todoId) {
        User user = (User) session.getAttribute("user");
        if(user == null) return false; // User not logged in

        Todo todo = todoRepository.findById(todoId).orElse(null);
        if(todo == null || !todo.getUser().getId().equals(user.getId())) return false;

        todoRepository.delete(todo);
        return true;
    }
}
