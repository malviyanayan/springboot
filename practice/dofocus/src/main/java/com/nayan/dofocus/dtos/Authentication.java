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


    // Title pattern:
    // - At least 1 visible character
    // - Allow letters, digits, spaces, and common punctuation (- _ : ; , . ' " ? !)
    // - Max length 100 characters
    Pattern TITLE_PATTERN = Pattern.compile(
            "^[A-Za-z0-9][A-Za-z0-9\\s\\-_:;,.'\"?!]{0,99}$"
    );

    // Notes pattern (maximum 1000 words)
    // - Words separated by whitespace
    // - Allows newlines, tabs, etc.
    Pattern NOTES_DESCRIPTION = Pattern.compile(
            "^\\s*(?:\\S+(?:\\s+|$)){1,1000}\\s*$",
            Pattern.UNICODE_CHARACTER_CLASS
    );

    APIResponse authenticate();
}
