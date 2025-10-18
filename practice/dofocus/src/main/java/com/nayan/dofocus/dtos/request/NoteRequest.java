package com.nayan.dofocus.dtos.request;

import com.nayan.dofocus.dtos.APIResponse;
import com.nayan.dofocus.dtos.Authentication;
import com.nayan.dofocus.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NoteRequest implements Authentication {

    private String title;
    private String content;
    private User user;

    @Override
    public APIResponse authenticate() {
        // User must be signed in
        if (user == null) return new APIResponse(
                "UNAUTHORIZED", HttpStatus.UNAUTHORIZED.value(),
                "Please Sign in",
                null
        );

        // Validate title
        if (title == null || title.trim().isEmpty()) return new APIResponse(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                "Please provide a valid Title",
                null
        );

        if (title.length() > 100) return new APIResponse(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                "Title too long (max 100 characters)",
                null
        );

        if (!TITLE_PATTERN.matcher(title).matches()) return new APIResponse(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                "Invalid Title format",
                null
        );

        // Validate description (notes)
        if (content == null || content.trim().isEmpty()) return new APIResponse(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                "Please provide a valid Content",
                null
        );

        if (!NOTES_DESCRIPTION.matcher(content).matches()) return new APIResponse(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                "Description must be between 1 and 1000 words",
                null
        );

        // Passed all validations
        return null;
    }
}
