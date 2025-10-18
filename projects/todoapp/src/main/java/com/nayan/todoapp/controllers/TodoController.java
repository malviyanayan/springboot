package com.nayan.todoapp.controllers;

import com.nayan.todoapp.dtos.TodoRequest;
import com.nayan.todoapp.entities.Todo;
import com.nayan.todoapp.entities.User;
import com.nayan.todoapp.repositories.TodoRepository;
import com.nayan.todoapp.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired private TodoRepository todoRepo;
    @Autowired private UserRepository userRepo;

    // List todos of current user
    @GetMapping
    public List<Todo> list(Principal principal) {
        return todoRepo.findByOwner_Username(principal.getName());
    }

    // Create a todo for current user
    @PostMapping
    public Todo create(@Valid @RequestBody TodoRequest req, Principal principal) {
        User owner = userRepo.findByUsername(principal.getName()).orElseThrow();
        Todo t = new Todo();
        t.setTitle(req.getTitle());
        t.setDescription(req.getDescription());
        t.setCompleted(false);
        t.setOwner(owner);
        return todoRepo.save(t);
    }

    // Update only if owned by the current user
    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody TodoRequest req, Principal principal) {
        Todo t = todoRepo.findByIdAndOwner_Username(id, principal.getName())
                .orElseThrow(() -> new RuntimeException("Todo not found or not yours"));

        if (req.getTitle() != null && !req.getTitle().isBlank()) t.setTitle(req.getTitle());
        if (req.getDescription() != null) t.setDescription(req.getDescription());
        if (req.getCompleted() != null) t.setCompleted(req.getCompleted());
        return todoRepo.save(t);
    }

    // Delete only if owned by the current user
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id, Principal principal) {
        Todo t = todoRepo.findByIdAndOwner_Username(id, principal.getName())
                .orElseThrow(() -> new RuntimeException("Todo not found or not yours"));
        todoRepo.delete(t);
        return "Deleted";
    }
}
