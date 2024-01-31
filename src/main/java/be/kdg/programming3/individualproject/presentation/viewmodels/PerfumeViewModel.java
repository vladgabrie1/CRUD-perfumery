package be.kdg.programming3.individualproject.presentation.viewmodels;

import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.PerfumeType;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Component
public class PerfumeViewModel {

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 25, message = "Name should have a length between 2 and 25 characters")
    private String name;

    @NotNull(message = "Please select a brand")
    private Brand brand;

    @PastOrPresent(message = "The date must be in the past or present")
    private LocalDate releaseDate;

    @NotNull(message = "Please select a type")
    private PerfumeType type;

    @DecimalMin(value = "0.0", message = "Price must be greater than 0")
    @DecimalMax(value = "100000.0", message = "There must be a mistake, the price is too high")
    private double price;
    @NotNull(message = "Please select a note")
    private List<Note> selectedNotes;

    private MultipartFile photo;


    public MultipartFile getPhoto() {
        return photo;
    }

    private List<Note> notes;

    private List<Brand> brands;

    public List<Note> getSelectedNotes() {
        return selectedNotes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
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

    @Override
    public String toString() {
        return "PerfumeViewModel{" +
                "name='" + name + '\'' +
                ", brand=" + brand +
                ", releaseDate=" + releaseDate +
                ", type=" + type +
                ", price=" + price +
                '}';
    }

    public void setSelectedNotes(List<Note> selectedNotes) {
        this.selectedNotes = selectedNotes;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
