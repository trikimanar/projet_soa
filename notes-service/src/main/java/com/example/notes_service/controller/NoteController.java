package com.example.notes_service.controller;

import com.example.notes_service.dto.NoteDto;
import com.example.notes_service.model.Note;
import com.example.notes_service.service.NoteService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

 
    private NoteDto toDto(Note n) {
        if (n == null) {
            return null; 
        }

        NoteDto d = new NoteDto();
        
        d.setId(n.getId());
        d.setStudentName(n.getStudentName() != null ? n.getStudentName() : "");
        d.setSubject(n.getSubject() != null ? n.getSubject() : "");
        d.setScore(n.getScore() != null ? n.getScore() : 0.0);
        d.setDateRecorded(n.getDateRecorded() != null ? n.getDateRecorded() : LocalDate.now());

        return d;
    }

    

    // Conversion DTO -> Model
    private Note toEntity(NoteDto d) {
        Note n = new Note();
        n.setStudentName(d.getStudentName());
        n.setSubject(d.getSubject());
        n.setScore(d.getScore());

        
        n.setDateRecorded(d.getDateRecorded() != null ? d.getDateRecorded() : LocalDate.now());

        return n;
    }

    @GetMapping
    public ResponseEntity<List<NoteDto>> getAll() {
        List<NoteDto> list = service.getAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getById(@PathVariable String id) {
        return ResponseEntity.ok(toDto(service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<NoteDto> create(@Valid @RequestBody NoteDto dto) {
        Note created = service.create(toEntity(dto));

        return ResponseEntity.created(URI.create("/api/notes/" + created.getId()))
                .body(toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> update(
            @PathVariable String id,
            @Valid @RequestBody NoteDto dto) {

        Note entity = toEntity(dto);
        entity.setId(id); // 🔥 IMPORTANT pour ne pas créer un nouveau document

        Note updated = service.update(id, entity);

        return ResponseEntity.ok(toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<NoteDto>> search(
            @RequestParam(required = false) String student,
            @RequestParam(required = false) String subject) {

        if (student != null) {
            return ResponseEntity.ok(
                    service.findByStudentName(student)
                            .stream().map(this::toDto)
                            .collect(Collectors.toList())
            );
        }

        if (subject != null) {
            return ResponseEntity.ok(
                    service.findBySubject(subject)
                            .stream().map(this::toDto)
                            .collect(Collectors.toList())
            );
        }

        return ResponseEntity.ok(service.getAll()
                .stream().map(this::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/test")
    public String test() {
        return "API fonctionne";
    }
}

