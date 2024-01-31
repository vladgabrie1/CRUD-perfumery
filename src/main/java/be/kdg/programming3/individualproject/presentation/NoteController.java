package be.kdg.programming3.individualproject.presentation;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.NoteGroup;
import be.kdg.programming3.individualproject.presentation.viewmodels.NoteViewModel;
import be.kdg.programming3.individualproject.service.NoteService;
import be.kdg.programming3.individualproject.service.SessionHistoryService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final NoteService noteService;
    private final SessionHistoryService historyService;

    public NoteController(NoteService noteService, SessionHistoryService historyService) {
        this.noteService = noteService;
        this.historyService = historyService;
    }

    @GetMapping("/list")
    public String getNotesView(Model model, HttpSession session,
                               @RequestParam(required = false) String filterName,
                               @RequestParam(required = false) NoteGroup filterGroup) {
        logger.info("Request for notes view");
        historyService.updateSessionHistory(session, "/note/list");

        List<Note> noteList;

        // Filtering notes based on parameters
        if (filterName != null && !filterName.isEmpty() && filterGroup != null) {
            noteList = noteService.getNotesByNameContainingAndGroup(filterName, filterGroup);
        } else if (filterName != null && !filterName.isEmpty()) {
            noteList = noteService.getNotesByNameContaining(filterName);
        } else if (filterGroup != null) {
            noteList = noteService.getNotesByGroup(filterGroup);
        } else {
            noteList = noteService.getAllNotes();
        }

        model.addAttribute("notes", noteList);
        logger.debug("Filtered notes retrieved successfully. Number of notes: {}", noteList.size());
        return "note/list";
    }

    @GetMapping("/add")
    public String addNote(Model model, HttpSession session){
        logger.info("Request for adding a note");
        historyService.updateSessionHistory(session, "/note/add");
        model.addAttribute("noteViewModel", new NoteViewModel());
        return "note/add";
    }

    @PostMapping("/add")
    public String processNote(@Valid @ModelAttribute("noteViewModel") NoteViewModel noteViewModel,
                              BindingResult bindingResult,
                              @RequestParam("photo") MultipartFile photoFile) {
        logger.debug("Processing note: {}", noteViewModel);

        // Handling validation errors
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> logger.error(error.toString()));
            return "note/add";
        } else {
            // Saving the note
            noteService.saveNote(new Note(
                    noteViewModel.getName(),
                    noteViewModel.getDescription(),
                    noteViewModel.getGroup())
            );


            logger.info("Note saved successfully: {}", noteViewModel);


            if (!photoFile.isEmpty()) {
                try {
                    String fileName = noteViewModel.getName().toLowerCase() + ".jpg";
                    byte[] bytes = photoFile.getBytes();
                    Path path = Paths.get("src/main/resources/static/img/notes/" + fileName);
                    Files.write(path, bytes);
                } catch (IOException e) {
                    // Handle file processing exception
                    logger.error(Arrays.toString(e.getStackTrace()));
                }
            }
            return "redirect:/note/list";
        }
    }

    @GetMapping("/details")
    public String notesDetails(@RequestParam("noteId") int id, Model model, HttpSession session){
        logger.info("Request for Notes Details");
        historyService.updateSessionHistory(session, "/note/details?noteId=" + id);

        // Fetching note details
        Note note = noteService.getNoteById(id);
        String photoURL = "/img/notes/" + note.getName().toLowerCase() + ".jpg";

        model.addAttribute("note", note);
        model.addAttribute("photoURL", photoURL);
        logger.debug("Note details retrieved successfully for Note ID: {}", id);
        return "note/details";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam("entityId") int id){
        // Deleting the note
        noteService.deleteNote(id);
        logger.info("Note deleted successfully. Note ID: {}", id);
        return "redirect:/note/list";
    }
}
