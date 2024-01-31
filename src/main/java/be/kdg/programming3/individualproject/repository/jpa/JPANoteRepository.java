package be.kdg.programming3.individualproject.repository.jpa;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.repository.NoteRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("prod")
@Primary
public class JPANoteRepository implements NoteRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Note> findAll() {
        logger.info("Retrieving all notes using JPA");
        List<Note> notes = em.createQuery("SELECT n FROM Note n", Note.class).getResultList();
        logger.debug("Number of notes retrieved: {}", notes.size());
        return notes;
    }

    @Override
    @Transactional
    public Note create(Note note) {
        logger.info("Creating note: {}", note);
        em.persist(note);
        logger.debug("Note created successfully");
        return note;
    }

    @Override
    @Transactional
    public void update(Note note) {
        logger.info("Updating note: {}", note);
        em.getTransaction().begin();
        em.merge(note);
        em.getTransaction().commit();
        logger.debug("Note updated successfully");
    }

    @Override
    public Note findById(int id) {
        logger.info("Retrieving note by ID: {}", id);
        Note note = em.find(Note.class, id);
        if (note == null) {
            logger.warn("Note with ID {} not found", id);
        }
        return note;
    }

    @Override
    @Transactional
    public void delete(int id) {
        logger.info("Deleting note with ID: {}", id);
        Note note = em.find(Note.class, id);
        em.remove(note);
        logger.debug("Note deleted successfully");
    }
}
