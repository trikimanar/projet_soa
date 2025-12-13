package com.example.notes_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.notes_service.model.Note;
import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findByStudentNameContainingIgnoreCase(String studentName);

    List<Note> findBySubjectIgnoreCase(String subject);
}
