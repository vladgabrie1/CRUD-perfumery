package be.kdg.programming3.individualproject.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToMany(cascade =
            {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                    CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "perfume_notes",
            joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "perfume_id"))
    private List<Perfume> perfumes;
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(name = "group_name")
    private NoteGroup group;

    public Note() {
    }

    public Note(int id, String name, String description, NoteGroup group) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.group = group;
    }

    public Note(String name, String description, NoteGroup group) {
        this.name = name;
        this.description = description;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Perfume> getPerfumes() {
        return perfumes;
    }

    public void setPerfumes(List<Perfume> perfumes) {
        this.perfumes = perfumes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NoteGroup getGroup() {
        return group;
    }

    public void setGroup(NoteGroup group) {
        this.group = group;
    }

    public void addPerfume(Perfume perfume) {
        if (perfumes==null) perfumes = new ArrayList<>();
        perfumes.add(perfume);
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", group=" + group +
                '}';
    }
}
