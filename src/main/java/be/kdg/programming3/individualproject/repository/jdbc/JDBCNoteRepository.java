package be.kdg.programming3.individualproject.repository.jdbc;

import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.NoteGroup;
import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
@Profile("dev")
public class JDBCNoteRepository implements NoteRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert noteInserter;

    public JDBCNoteRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.noteInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("NOTES")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public List<Note> findAll() {
        String sql = "SELECT * FROM NOTES";
        List<Note> notes = jdbcTemplate.query(sql, JDBCNoteRepository::mapRow);
        notes.forEach(this::loadPerfumes);
        return notes;
    }

    @Override
    public Note create(Note note) {
        int noteId = noteInserter.executeAndReturnKey(Map.of(
                "NAME", note.getName(),
                "DESCRIPTION", note.getDescription(),
                "GROUP_NAME", note.getGroup().toString()
        )).intValue();
        note.setId(noteId);
        logger.debug("JDBCRepository: note created:  " + note);
        return note;
    }

    @Override
    public Note findById(int id) {
        Note note = jdbcTemplate.queryForObject("SELECT * FROM NOTES WHERE ID = ?",
                JDBCNoteRepository::mapRow,
                id);
        if (note != null) {
            loadPerfumes(note);
        }
        return note;
    }

    @Override
    public void update(Note note) {
        String sql = "UPDATE NOTES SET NAME = ?, DESCRIPTION = ?, group_name = ? WHERE ID = ?";
        jdbcTemplate.update(sql, note.getName(), note.getDescription(), note.getGroup().ordinal(), note.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM NOTES WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private void loadPerfumes(Note note) {
        List<Perfume> perfumes = jdbcTemplate.query("SELECT * FROM PERFUMES WHERE ID IN " +
                        "(SELECT PERFUME_ID FROM PERFUME_NOTES WHERE NOTE_ID = ?) ",
                JDBCPerfumeRepository::mapRow,
                note.getId());
        perfumes.forEach(this::loadBrand);
        note.setPerfumes(perfumes);
    }

    private void loadBrand(Perfume perfume){
        try{
            Brand brand = jdbcTemplate.queryForObject("SELECT BRANDS.ID, BRANDS.NAME " +
                            "FROM BRANDS INNER JOIN PERFUMES on BRANDS.ID = PERFUMES.BRAND_ID AND PERFUMES.ID = ?",
                    JDBCBrandRepository::mapRow,
                    perfume.getId());

            perfume.setBrand(brand);
        } catch (EmptyResultDataAccessException e){
            logger.error("JDBCPerfumeRep:" + e.getMessage());
        }
    }

    public static Note mapRow(ResultSet rs, int i) throws SQLException {
        return new Note(
          rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"),
                NoteGroup.valueOf(rs.getString("group_name"))
        );
    }
}
