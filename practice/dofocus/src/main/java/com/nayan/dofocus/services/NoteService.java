package com.nayan.dofocus.services;

import com.nayan.dofocus.entities.Note;
import com.nayan.dofocus.entities.User;
import com.nayan.dofocus.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public Note saveNote(Note note){
        return noteRepository.save(note);
    }

    public List<Note> getNotesByUser(User user){
        return noteRepository.findByUserOrderByCreatedAtDesc(user);
    }

    public Note findById(Long id){
        return noteRepository.findById(id).orElse(null);
    }

    public void deleteNote(Note note){
        noteRepository.delete(note);
    }
}
