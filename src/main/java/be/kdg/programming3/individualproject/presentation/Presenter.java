package be.kdg.programming3.individualproject.presentation;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.service.NoteService;
import be.kdg.programming3.individualproject.service.PerfumeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Presenter {
    private final PerfumeService perfumeService;
    private final NoteService noteService;
    private final Scanner scanner = new Scanner(System.in);

    public Presenter(PerfumeService perfumeService, NoteService noteService) {
        this.perfumeService = perfumeService;
        this.noteService = noteService;
    }

    void showAllPerfumes() {
        System.out.println("All perfumes");
        System.out.println("===========");
        perfumeService.getAllPerfumes().forEach(System.out::println);
        System.out.println("Writing to json...");
        perfumeService.writePerfumesToJson(perfumeService.getAllPerfumes());
    }

    void showPerfumesByBrandOrYear(){
        System.out.print("Enter brand name or leave blank: ");
        Scanner scanner = new Scanner(System.in);
        String brandName = scanner.nextLine().trim();

        System.out.print("Enter released date year or leave blank: ");
        String yearInput = scanner.nextLine().trim();

        List<Perfume> filteredPerfumes = perfumeService.getPerfumesByBrandOrYear(brandName, yearInput);

        System.out.println("Perfumes matching criteria:");
        System.out.println("===========================");
        filteredPerfumes.forEach(System.out::println);

        System.out.println("Writing to json...");
        perfumeService.writePerfumesToJson(filteredPerfumes);
    }

    void showAllNotes(){
        System.out.println("All notes");
        System.out.println("===========");
        noteService.getAllNotes().forEach(System.out::println);
        System.out.println("Writing to json...");
        noteService.writeToJson(noteService.getAllNotes());
    }

    void showNotesByName(){
        System.out.print("Enter note name: ");
        String noteName = scanner.nextLine();

        List<Note> filteredNotes = noteService.getNoteByName(noteName);

        System.out.println("Notes by Name: " + noteName);
        System.out.println("=======================");
        filteredNotes.forEach(System.out::println);

        System.out.println("Writing to json...");
        noteService.writeToJson(filteredNotes);

    }

}
