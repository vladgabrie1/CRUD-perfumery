package be.kdg.programming3.individualproject.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "Perfumes")
public class Perfume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "perfume_notes",
            joinColumns = @JoinColumn(name = "perfume_id"),
            inverseJoinColumns = @JoinColumn(name = "note_id"))
    private List<Note> notes;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private PerfumeType type;

    private double price;

    public Perfume() {
    }

    public Perfume(int id, String name, Brand brand, LocalDate releaseDate, PerfumeType type, double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.releaseDate = releaseDate;
        this.type = type;
        this.price = price;
        this.notes = new ArrayList<Note>();
    }

    public Perfume(String name, Brand brand, List<Note> notes, LocalDate releaseDate, PerfumeType type, double price) {
        this.name = name;
        this.brand = brand;
        this.notes = notes;
        this.releaseDate = releaseDate;
        this.type = type;
        this.price = price;
    }

    public Perfume(String name, Brand brand, LocalDate releaseDate, PerfumeType type, double price) {
        this.name = name;
        this.brand = brand;
        this.releaseDate = releaseDate;
        this.type = type;
        this.price = price;
    }

    public Perfume(int id, String name, LocalDate releaseDate, PerfumeType type, double price) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.type = type;
        this.price = price;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
//        notes.forEach(note -> note.addPerfume(this));
        this.notes = notes;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public PerfumeType getType() {
        return type;
    }

    public void setType(PerfumeType type) {
        this.type = type;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void addNote(Note note) {
        notes.add(note);
        note.addPerfume(this);
    }

    @Override
    public String toString() {
        return "Perfume{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand=" + brand +
                ", releaseDate=" + releaseDate +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
