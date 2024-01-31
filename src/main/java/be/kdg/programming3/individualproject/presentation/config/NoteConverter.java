package be.kdg.programming3.individualproject.presentation.config;


import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteConverter implements Converter<String, Note> {
    private final NoteService noteService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public NoteConverter(NoteService noteService) {
        this.noteService = noteService;
    }

    @Override
    public Note convert(String noteId) {
        try {
            if(noteId == null){
                logger.error("Note is null");
            }
            int id = Integer.parseInt(noteId);
            return noteService.getNoteById(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid noteId");
        }
    }

}
