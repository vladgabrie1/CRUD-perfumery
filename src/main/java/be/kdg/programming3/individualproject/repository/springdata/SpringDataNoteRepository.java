package be.kdg.programming3.individualproject.repository.springdata;

import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.NoteGroup;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("springdata")
public interface SpringDataNoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByGroup(NoteGroup group);
    List<Note> findByNameContaining(String part);

    // Custom query to find notes by group and name containing a specified part
    @Query("SELECT n FROM Note n WHERE n.group = :group AND LOWER(n.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Note> findByGroupAndNameContaining(@Param("group") NoteGroup group,
                                            @Param("name") String name);

}
