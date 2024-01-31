package be.kdg.programming3.individualproject.service;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.NoteGroup;
import jakarta.transaction.Transactional;

import java.util.List;

public interface NoteService {
    List<Note> getAllNotes();
    List<Note> getNoteByName(String noteName);
    void writeToJson(List<Note> note);
    void saveNote(Note newNote);
    Note getNoteById(int id);
    void deleteNote(int id);
    List<Note> getNotesByNameContaining(String filterName);
    List<Note> getNotesByGroup(NoteGroup filterGroup);
    List<Note> getNotesByNameContainingAndGroup(String filterName, NoteGroup filterGroup);
}
