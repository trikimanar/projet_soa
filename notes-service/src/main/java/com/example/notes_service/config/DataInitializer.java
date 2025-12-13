package com.example.notes_service.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.notes_service.model.Note;
import com.example.notes_service.repository.NoteRepository;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(NoteRepository repository) {
        return args -> {
            repository.deleteAll();

            repository.save(new Note("Alice", "Mathématiques", 15.0, LocalDate.now()));
            repository.save(new Note("Bob", "Physique", 12.5, LocalDate.now()));
            repository.save(new Note("Charlie", "Informatique", 18.0, LocalDate.now()));

            System.out.println(">>> Données initiales MongoDB insérées !");
        };
    }
}


