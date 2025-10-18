package com.nayan.dofocus.entities;

import com.nayan.dofocus.dtos.request.TodoRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Long id;

    @Column(name = "todo_name", length = 50, nullable = false)
    private String todoName;

    @Column(name = "is_done")
    private Boolean isDone = false;

//    Completion Date
    @Column(name = "completion_date", nullable = true)
    private Date completionDate;

//    Notify Me
    private NotifyMe notifyMe;

//    Type
    @Column(name = "growth", nullable = true )
    private Integer growth;


    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp createdAt;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo(TodoRequest todoRequest){
        this.todoName = todoRequest.getTodoName();
        this.user = todoRequest.getUser();
    }

}
