package be.kdg.programming3.individualproject.repository.list;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.repository.NoteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("collections")
public class ListNoteRepository implements NoteRepository {
    List<Note> notes = new ArrayList<>();
    @Override
    public List<Note> findAll() {
        return notes;
    }

    @Override
    public Note findById(int id) {
        return notes.stream().filter(n -> n.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Note create(Note note) {
        note.setId(notes.stream().mapToInt(Note::getId).max().orElse(0) + 1);
        notes.add(note);
        return note;
    }

    @Override
    public void update(Note note) {

    }

    @Override
    public void delete(int id) {

    }
}
