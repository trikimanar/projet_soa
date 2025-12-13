package com.example.notes_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "notes")
public class Note {

    @Id
    private String id;

    private String studentName;
    private String subject;
    private Double score;
    private LocalDate dateRecorded;

    public Note() {}

    public Note(String studentName, String subject, Double score, LocalDate dateRecorded) {
        this.studentName = studentName;
        this.subject = subject;
        this.score = score;
        this.dateRecorded = dateRecorded;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public Double getScore() { return score; }
    public void setScore(Double score) { this.score = score; }

    public LocalDate getDateRecorded() { return dateRecorded; }
    public void setDateRecorded(LocalDate dateRecorded) { this.dateRecorded = dateRecorded; }
}

