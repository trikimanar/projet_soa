package com.example.notes_service.service;

import com.example.notes_service.model.Note;
import java.util.List;

public interface NoteService {

    Note create(Note note);

    Note update(String id, Note note);

    Note getById(String id);

    List<Note> getAll();

    void delete(String id);

    List<Note> findByStudentName(String studentName);

    List<Note> findBySubject(String subject);
}
