package com.nayan.dofocus.dtos.response;

import com.nayan.dofocus.entities.Todo;
import com.nayan.dofocus.entities.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@ToString
public class TodoResponse {
    private Long id;
    private String todoName;
    private Boolean isDone ;
    private Timestamp createdAt;

    public TodoResponse(Long id, String todoName, Boolean isDone, Timestamp createdAt) {
        this.id = id;
        this.todoName = todoName;
        this.isDone = isDone;
        this.createdAt = createdAt;
    }

    public static List<TodoResponse> getTodo(List<Todo> todos){
        List<TodoResponse> myTodos = new ArrayList<>();
        for(Todo todo : todos){
            myTodos.add(new TodoResponse(todo.getId(), todo.getTodoName(), todo.getIsDone(), todo.getCreatedAt()));
        }
        return myTodos;
    }
}
