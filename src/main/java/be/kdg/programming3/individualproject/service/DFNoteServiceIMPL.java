package be.kdg.programming3.individualproject.service;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.NoteGroup;
import be.kdg.programming3.individualproject.repository.NoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Profile("!springdata")
public class DFNoteServiceIMPL implements NoteService{
    NoteRepository noteRepository;
    JsonWriter writer;

    public DFNoteServiceIMPL(NoteRepository noteRepository, JsonWriter writer) {
        this.noteRepository = noteRepository;
        this.writer = writer;
    }

    @Override
    public Note getNoteById(int id) {
        return noteRepository.findById(id);
    }

    @Override
    @Transactional
    public void saveNote(Note newNote) {
        noteRepository.create(newNote);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public void writeToJson(List<Note> notes) {
        writer.writeNotes(notes);
    }

    @Override
    public List<Note> getNoteByName(String noteName) {
        return noteRepository.findAll().stream().filter(note -> note.getName().toLowerCase().contains(noteName.toLowerCase())).toList();
    }

    @Override
    public void deleteNote(int id) {
        noteRepository.delete(id);
    }

    @Override
    public List<Note> getNotesByNameContaining(String filterName) {
        return noteRepository.findAll().stream().filter(note -> note.getName().contains(filterName)).collect(Collectors.toList());
    }

    @Override
    public List<Note> getNotesByGroup(NoteGroup filterGroup) {
        return noteRepository.findAll().stream().filter(note -> note.getGroup().equals(filterGroup)).collect(Collectors.toList());
    }

    @Override
    public List<Note> getNotesByNameContainingAndGroup(String filterName, NoteGroup filterGroup) {
        return noteRepository.findAll().stream().filter(note -> note.getName().contains(filterName) & note.getGroup().equals(filterGroup)).toList();
    }
}
