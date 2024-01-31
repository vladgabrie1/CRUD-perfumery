package be.kdg.programming3.individualproject.repository;

import be.kdg.programming3.individualproject.domain.Note;

import java.util.List;

public interface NoteRepository {
    List<Note> findAll();
    Note create(Note note);
    void update(Note note);
    Note findById(int id);
    void delete(int id);
}
