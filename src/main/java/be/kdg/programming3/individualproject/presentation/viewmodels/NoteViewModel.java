package be.kdg.programming3.individualproject.presentation.viewmodels;

import be.kdg.programming3.individualproject.domain.NoteGroup;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

public class NoteViewModel {
    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 25, message = "Name should have a length between 2 and 25 characters")
    private String name;

    @NotNull(message = "Please select a group")
    private NoteGroup group;

    @NotBlank(message = "Description is mandatory")
    @Size(min = 4, max = 500, message = "Description should have a length between 4 and 500 characters")
    private String description;

    private MultipartFile photo;


    public MultipartFile getPhoto() {
        return photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NoteGroup getGroup() {
        return group;
    }


    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return "NoteViewModel{" +
                "name='" + name + '\'' +
                ", group=" + group +
                ", description='" + description + '\'' +
                '}';
    }

    public void setGroup(NoteGroup group) {
        this.group = group;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
