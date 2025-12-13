package com.example.notes_service.service;

import com.example.notes_service.exception.ResourceNotFoundException;
import com.example.notes_service.model.Note;
import com.example.notes_service.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repo;

    public NoteServiceImpl(NoteRepository repo) {
        this.repo = repo;
    }

    @Override
    public Note create(Note note) {
        return repo.save(note);
    }

    @Override
    public Note update(String id, Note note) {

        Note existing = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note introuvable : id=" + id));

        existing.setStudentName(note.getStudentName());
        existing.setSubject(note.getSubject());
        existing.setScore(note.getScore());
        existing.setDateRecorded(note.getDateRecorded());

        return repo.save(existing);
    }

    @Override
    public Note getById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Note introuvable : id=" + id));
    }

    @Override
    public List<Note> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(String id) {
        repo.delete(getById(id));
    }

    @Override
    public List<Note> findByStudentName(String studentName) {
        return repo.findByStudentNameContainingIgnoreCase(studentName);
    }

    @Override
    public List<Note> findBySubject(String subject) {
        return repo.findBySubjectIgnoreCase(subject);
    }

    }

