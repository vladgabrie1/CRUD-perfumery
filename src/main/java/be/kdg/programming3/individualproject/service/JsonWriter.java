package be.kdg.programming3.individualproject.service;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.util.LocalDateTypeAdapter;
import com.google.gson.*;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
public class JsonWriter {
    private static final String PERFUMES_JSON = "perfumes.json";
    private static final String NOTES_JSON = "notes.json";

    private final Gson gson;

    public JsonWriter() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter());
        gson = builder.create();
    }

    public void writePerfumes(List<Perfume> perfumes){
        String json = gson.toJson(perfumes);
        try(FileWriter writer = new FileWriter(PERFUMES_JSON)){
            writer.write(json);
        } catch (IOException e){
            throw new RuntimeException("Unable to save perfume to JSON", e);
        }
    }

    public void writeNotes(List<Note> notes){
        String json = gson.toJson(notes);
        try(FileWriter writer = new FileWriter(NOTES_JSON)){
            writer.write(json);
        } catch (IOException e){
            throw new RuntimeException("Unable to save notes to JSON", e);
        }
    }

}

