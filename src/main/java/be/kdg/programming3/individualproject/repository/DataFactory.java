package be.kdg.programming3.individualproject.repository;

import be.kdg.programming3.individualproject.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("collections")
public class DataFactory implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final PerfumeRepository perfumeRepository;
    private final NoteRepository noteRepository;
    private final BrandRepository brandRepository;

    public DataFactory(PerfumeRepository perfumeRepository, NoteRepository noteRepository, BrandRepository brandRepository) {
        this.perfumeRepository = perfumeRepository;
        this.noteRepository = noteRepository;
        this.brandRepository = brandRepository;
    }

    public void seed() {
        logger.warn("DataFactory is working");
        List<Note> notes = new ArrayList<>();
        List<Perfume> perfumes = new ArrayList<>();
        List<Brand> brands = new ArrayList<>();

        Brand chanel = new Brand(1, "Chanel");
        Brand dior = new Brand(2, "Dior");
        Brand guerlain = new Brand(3, "Guerlain");
        Brand versace = new Brand(4, "Versace");

        Note note1 = new Note("Rose", "Sweet and floral scent", NoteGroup.FLORAL);
        Note note2 = new Note("Bergamot", "Citrusy and refreshing", NoteGroup.CITRUS);
        Note note3 = new Note("Vanilla", "Sweet and comforting", NoteGroup.GOURMAND);
        Note note4 = new Note("Sandalwood", "Warm and woody", NoteGroup.WOODY);
        Note note5 = new Note("Jasmine", "Intensely floral", NoteGroup.FLORAL);
        Note note6 = new Note("Pepper", "Spicy and aromatic", NoteGroup.SPICY);
        Note note7 = new Note("Ocean Breeze", "Fresh and aquatic", NoteGroup.AQUATIC);
        Note note8 = new Note("Lavender", "Herbal and calming", NoteGroup.HERBAL);


        Perfume chanelNo5 = new Perfume(1, "Chanel No. 5", chanel, LocalDate.of(1921, 5, 5), PerfumeType.EAU_DE_PERFUME,  120.50);
        Perfume diorJadore = new Perfume(2, "Dior J'adore", dior, LocalDate.of(1999, 10, 1), PerfumeType.EAU_DE_PERFUME,  150.75);
        Perfume guerlainShalimar = new Perfume(3, "Guerlain Shalimar", guerlain, LocalDate.of(1925, 4, 17), PerfumeType.EAU_DE_PERFUME,  90.25);
        Perfume versaceBrightCrystal = new Perfume(4, "Versace Bright Crystal", versace, LocalDate.of(2006, 12, 20), PerfumeType.EAU_DE_TOILETTE,  80.00);
        Perfume chanelCocoMademoiselle = new Perfume(5, "Chanel Coco Mademoiselle", chanel, LocalDate.of(2001, 9, 30), PerfumeType.EAU_DE_PERFUME,  130.00);
        Perfume diorSauvage = new Perfume(6, "Dior Sauvage", dior, LocalDate.of(2015, 8, 30), PerfumeType.EAU_DE_TOILETTE,  110.50);
        Perfume guerlainMonGuerlain = new Perfume(7, "Guerlain Mon Guerlain", guerlain, LocalDate.of(2017, 2, 22), PerfumeType.EAU_DE_PERFUME,  95.75);

        brands.add(chanel);
        brands.add(dior);
        brands.add(guerlain);
        brands.add(versace);

        chanelNo5.addNote(note1);
        chanelNo5.addNote(note4);
        chanelNo5.addNote(note5);
        diorJadore.addNote(note1);
        diorJadore.addNote(note5);
        guerlainShalimar.addNote(note1);
        guerlainShalimar.addNote(note3);
        guerlainShalimar.addNote(note5);
        guerlainShalimar.addNote(note2);
        versaceBrightCrystal.addNote(note1);
        chanelCocoMademoiselle.addNote(note2);
        chanelCocoMademoiselle.addNote(note1);
        chanelCocoMademoiselle.addNote(note5);
        chanelCocoMademoiselle.addNote(note3);
        diorSauvage.addNote(note2);
        diorSauvage.addNote(note8);
        diorSauvage.addNote(note3);
        guerlainMonGuerlain.addNote(note2);
        guerlainMonGuerlain.addNote(note8);
        guerlainMonGuerlain.addNote(note1);
        guerlainMonGuerlain.addNote(note5);
        guerlainMonGuerlain.addNote(note3);


        perfumes.add(chanelNo5);
        perfumes.add(diorJadore);
        perfumes.add(guerlainShalimar);
        perfumes.add(versaceBrightCrystal);
        perfumes.add(chanelCocoMademoiselle);
        perfumes.add(diorSauvage);
        perfumes.add(guerlainMonGuerlain);

        notes.add(note1);
        notes.add(note2);
        notes.add(note3);
        notes.add(note4);
        notes.add(note5);
        notes.add(note6);
        notes.add(note7);
        notes.add(note8);

        perfumes.forEach(perfumeRepository::create);
        notes.forEach(noteRepository::create);
        brands.forEach(brandRepository::create);
    }


    public void run(String... args) throws Exception {
        seed();
    }
}
