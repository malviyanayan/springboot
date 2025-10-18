package com.nayan.dofocus.repositories;

import com.nayan.dofocus.entities.Note;
import com.nayan.dofocus.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Fetch all notes by a specific user
    List<Note> findByUser(User user);

    // Optional: fetch notes in descending order of creation date
    List<Note> findByUserOrderByCreatedAtDesc(User user);
}
