package com.nayan.dofocus.dtos;

import java.util.regex.Pattern;

public interface Authentication {
     Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
     Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]{3,}(?: [A-Za-z]+)*$");
     Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,}$"
     );
    Pattern TODO_PATTERN =
            Pattern.compile("^[a-zA-Z0-9\\s.,!?_-]{3,50}$");



    APIResponse authenticate();
}
