package com.nayan.dofocus.controllers.users.notes;

import com.nayan.dofocus.dtos.APIResponse;
import com.nayan.dofocus.dtos.request.NoteRequest;
import com.nayan.dofocus.dtos.response.NoteResponse;
import com.nayan.dofocus.entities.Note;
import com.nayan.dofocus.entities.User;
import com.nayan.dofocus.jwt.JwtUtils;
import com.nayan.dofocus.services.NoteService;
import com.nayan.dofocus.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/notes")
public class NotesController {

    @Autowired
    private NoteService noteService; // service to save notes

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtService;

    @DeleteMapping("/delete_my_note/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable("id") Long id) {

        // Authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            user = userService.findByEmail(email);
        }

        if (user == null) {
            return new ResponseEntity<>(new APIResponse(
                    "UNAUTHORIZED",
                    HttpStatus.UNAUTHORIZED.value(),
                    "Please Sign in",
                    null
            ), HttpStatus.OK);
        }

        // Fetch note
        Note note = noteService.findById(id);
        if (note == null || !note.getUser().getId().equals(user.getId())) {
            return new ResponseEntity<>(new APIResponse(
                    "NOT_FOUND",
                    HttpStatus.NOT_FOUND.value(),
                    "Note not found",
                    null
            ), HttpStatus.OK);
        }

        // Delete note
        noteService.deleteNote(note);

        return new ResponseEntity<>(new APIResponse(
                "OK",
                HttpStatus.OK.value(),
                "Note deleted successfully",
                null
        ), HttpStatus.OK);
    }


    @PutMapping("/update_my_note/{id}")
    public ResponseEntity<?> updateNote(@PathVariable("id") Long id, @RequestBody NoteRequest noteRequest) {

        // Authenticate user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            user = userService.findByEmail(email);
        }

        if (user == null) {
            return new ResponseEntity<>(new APIResponse(
                    "UNAUTHORIZED",
                    HttpStatus.UNAUTHORIZED.value(),
                    "Please Sign in",
                    null
            ), HttpStatus.OK);
        }

        // Set user in NotesRequest for validation
        noteRequest.setUser(user);

        // Validate request
        APIResponse validationResponse = noteRequest.authenticate();
        if (validationResponse != null) {
            return new ResponseEntity<>(validationResponse, HttpStatus.OK);
        }

        // Fetch existing note
        Note existingNote = noteService.findById(id);
        if (existingNote == null || !existingNote.getUser().getId().equals(user.getId())) {
            return new ResponseEntity<>(new APIResponse(
                    "NOT_FOUND",
                    HttpStatus.NOT_FOUND.value(),
                    "Note not found",
                    null
            ), HttpStatus.OK);
        }

        // Update fields from request
        existingNote.setTitle(noteRequest.getTitle());
        existingNote.setContent(noteRequest.getContent());

        // Save updated note
        Note newNote = noteService.saveNote(existingNote);
        NoteResponse noteResponse = new NoteResponse(newNote);


        return new ResponseEntity<>(new APIResponse(
                "OK",
                HttpStatus.OK.value(),
                "Note updated successfully",
                noteResponse
        ), HttpStatus.OK);
    }



    @GetMapping("/my_notes")
    public ResponseEntity<?> getAllNotes() {

        // Extract authenticated user from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            user = userService.findByEmail(email);
        }

        // Check if user is authenticated
        if (user == null) {
            return new ResponseEntity<>(new APIResponse(
                    "UNAUTHORIZED",
                    HttpStatus.UNAUTHORIZED.value(),
                    "Please Sign in",
                    null
            ), HttpStatus.OK);
        }

        // Fetch notes for user
        List<Note> notes = noteService.getNotesByUser(user);

        // Map to NoteResponse
        List<NoteResponse> responseList = NoteResponse.getNotesResponse(notes);

        // Return
        return new ResponseEntity<>(new APIResponse(
                "OK",
                HttpStatus.OK.value(),
                "Notes fetched successfully",
                responseList
        ), HttpStatus.OK);
    }


    @PostMapping("/add_my_note")
    public ResponseEntity<APIResponse> addNotes(@RequestBody NoteRequest notesRequest, HttpServletRequest request) {

        // Extract authenticated user from SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            user = userService.findByEmail(email);
        }

        // Set user in NotesRequest for validation
        notesRequest.setUser(user);

        // Validate request
        APIResponse response = notesRequest.authenticate();
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        // Save note using service
        Note note = new Note(notesRequest);

        Note newNote = noteService.saveNote(note);

        return new ResponseEntity<>(new APIResponse(
                "OK",
                HttpStatus.OK.value(),
                "Note saved successfully",
                new NoteResponse(newNote)
        ), HttpStatus.OK);
    }

}
