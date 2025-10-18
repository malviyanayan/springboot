package com.nayan.todoapp.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TodoRequest {
    @NotBlank
    private String title;
    private String description;
    private Boolean completed; // optional for update
}
