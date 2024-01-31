package be.kdg.programming3.individualproject.service.springdata;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.NoteGroup;
import be.kdg.programming3.individualproject.repository.springdata.SpringDataNoteRepository;
import be.kdg.programming3.individualproject.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdata")
public class SpringDataNoteService implements NoteService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SpringDataNoteRepository noteRepository;

    public SpringDataNoteService(SpringDataNoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public List<Note> getAllNotes() {
        logger.info("Retrieving all notes");
        List<Note> notes = noteRepository.findAll(Sort.by("name"));
        logger.debug("Number of notes retrieved: {}", notes.size());
        return notes;
    }

    @Override
    public List<Note> getNoteByName(String noteName) {
        return noteRepository.findByNameContaining(noteName);
    }

    @Override
    public void writeToJson(List<Note> note) {

    }

    @Override
    public void saveNote(Note newNote) {
        logger.info("Saving note: {}", newNote);
        noteRepository.save(newNote);
        logger.debug("Note saved successfully");
    }

    @Override
    public Note getNoteById(int id) {
        logger.info("Retrieving note by ID: {}", id);
        Note note = noteRepository.findById(id).orElse(null);
        if (note == null) {
            logger.warn("Note with ID {} not found", id);
        }
        return note;
    }

    @Override
    public void deleteNote(int id) {
        logger.warn("Deleting note with ID: {}", id);
        noteRepository.deleteById(id);
        logger.debug("Note deleted successfully");
    }

    @Override
    public List<Note> getNotesByNameContaining(String filterName) {
        logger.info("Retrieving notes by name containing: {}", filterName);
        return noteRepository.findByNameContaining(filterName);
    }

    @Override
    public List<Note> getNotesByGroup(NoteGroup filterGroup) {
        logger.info("Retrieving notes by group: {}", filterGroup);
        return noteRepository.findByGroup(filterGroup);
    }

    @Override
    public List<Note> getNotesByNameContainingAndGroup(String filterName, NoteGroup filterGroup) {
        logger.info("Retrieving notes by name containing: {} and group: {}", filterName, filterGroup);
        return noteRepository.findByGroupAndNameContaining(filterGroup, filterName);
    }
}
