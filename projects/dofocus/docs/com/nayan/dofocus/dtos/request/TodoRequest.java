package com.nayan.dofocus.dtos.request;

import com.nayan.dofocus.dtos.APIResponse;
import com.nayan.dofocus.dtos.Authentication;
import com.nayan.dofocus.entities.NotifyMe;
import com.nayan.dofocus.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter
@Setter
public class TodoRequest implements Authentication {

    private String todoName;
    private User user;

    private String completionDate;

    private String notifyMe;

    @Override
    public APIResponse authenticate() {
        if (user == null) return new APIResponse(
                "UNAUTHORIZED", HttpStatus.UNAUTHORIZED.value(),
                "Please Sign in",
                null
        );

        if (todoName == null || todoName.trim().isEmpty()) return new APIResponse(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                "Please provide a valid Todo name",
                null
        );

        if (todoName.length() > 100) return new APIResponse(
                "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                "Todo name too long (max 100 characters)",
                null
        );

        if (!TODO_PATTERN.matcher(todoName).matches())
            return new APIResponse(
                    "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                    "Invalid Name format",
                    null
            );

        if (completionDate != null) {
            try {
                LocalDate date = LocalDate.parse(completionDate);
                if (date.isBefore(LocalDate.now())) {
                    return new APIResponse(
                            "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                            "Completion date cannot be in the past",
                            null
                    );
                }
            } catch (DateTimeParseException e) {
                return new APIResponse(
                        "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                        "Invalid completion date format (expected yyyy-MM-dd)",
                        null
                );
            }
        }

        // validate notifyMe
        if (notifyMe != null) {
            try {
                NotifyMe.valueOf(notifyMe);
            } catch (IllegalArgumentException e) {
                return new APIResponse(
                        "BAD_REQUEST", HttpStatus.BAD_REQUEST.value(),
                        "Invalid notifyMe value (Allowed: DISABLE, EVERYDAY, AFTER_TIMEOUT)",
                        null
                );
            }
        }

        return null;
    }

}
