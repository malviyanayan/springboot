package com.nayan.dofocus.dtos.response;

import com.nayan.dofocus.entities.Note;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class NoteResponse {
    private Long id;
    private String title;
    private String content;
    private Timestamp createdAt;

    public NoteResponse(){

    }

    public NoteResponse(Note note){
        this.id = note.getId();
        this.title = note.getTitle();
        this.content = note.getContent();
        this.createdAt = note.getCreatedAt();
    }

    public static List<NoteResponse> getNotesResponse(List<Note> notes){
        List<NoteResponse> response = new ArrayList<>();
        for(Note note : notes){
            NoteResponse temp = new NoteResponse();
            temp.setId(note.getId());
            temp.setTitle(note.getTitle());
            temp.setContent(note.getContent());
            temp.setCreatedAt(note.getCreatedAt());
            response.add(temp);
        }
        return response;
    }
}
