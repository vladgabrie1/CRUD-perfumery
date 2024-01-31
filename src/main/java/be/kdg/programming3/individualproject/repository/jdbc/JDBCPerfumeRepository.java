package be.kdg.programming3.individualproject.repository.jdbc;

import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.domain.PerfumeType;
import be.kdg.programming3.individualproject.repository.PerfumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("dev")
public class JDBCPerfumeRepository implements PerfumeRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert perfumeInserter;

    public JDBCPerfumeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.perfumeInserter =  new SimpleJdbcInsert(jdbcTemplate).withTableName("PERFUMES")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public List<Perfume> findAll() {
        String sql = "SELECT * FROM PERFUMES";
        List<Perfume> perfumes = jdbcTemplate.query(sql, JDBCPerfumeRepository::mapRow);
        perfumes.forEach(this::loadNotes);
        perfumes.forEach( this::loadBrand);
        return perfumes;
    }

    @Override
    public List<Perfume> getPerfumesByPriceRangeAndReleaseDateAfter(Double minPrice, Double maxPrice, LocalDate releaseDate) {
        String sql = "SELECT * FROM PERFUMES WHERE price BETWEEN ? AND ? AND release_date > ?";

        List<Perfume> perfumes = jdbcTemplate.query(sql, new Object[]{minPrice, maxPrice, releaseDate},
                JDBCPerfumeRepository::mapRow);

        perfumes.forEach(this::loadNotes);
        perfumes.forEach(this::loadBrand);

        return perfumes;
    }

    @Override
    public List<Perfume> getPerfumesByPriceRange(Double minPrice, Double maxPrice) {
        String sql = "SELECT * FROM PERFUMES WHERE price BETWEEN ? AND ?";
        List<Perfume> perfumes = jdbcTemplate.query(sql, new Object[]{minPrice, maxPrice},
                JDBCPerfumeRepository::mapRow);

        perfumes.forEach(this::loadNotes);
        perfumes.forEach(this::loadBrand);

        return perfumes;
    }

    @Override
    public Perfume create(Perfume perfume) {
        logger.debug("JDBCRepository: creating perfume:  " + perfume);
        int perfumeId = perfumeInserter.executeAndReturnKey(Map.of(
                "NAME", perfume.getName(),
                "BRAND_ID", perfume.getBrand().getId(),
                "RELEASE_DATE", perfume.getReleaseDate(),
                "TYPE", perfume.getType().toString(),
                "PRICE", perfume.getPrice()
        )).intValue();
        perfume.setId(perfumeId);
        logger.debug("JDBCRepository: perfume created:  " + perfume);
        for (Note note : perfume.getNotes()) {
            jdbcTemplate.update("INSERT INTO Perfume_Notes (PERFUME_ID, NOTE_ID) VALUES (?, ?)",
                    perfume.getId(), note.getId());
        }

        return perfume;
    }


    @Override
    public void update(Perfume perfume) {
        String sql = "UPDATE PERFUMES SET NAME = ?, BRAND_ID = ?, RELEASE_DATE = ?, TYPE = ?, PRICE = ? WHERE ID = ?";
        jdbcTemplate.update(sql, perfume.getName(), perfume.getBrand().getId(), perfume.getReleaseDate(),
                perfume.getType().name(), perfume.getPrice(), perfume.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM PERFUMES WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Perfume findById(int id) {
        Perfume perfume = jdbcTemplate.queryForObject("SELECT * FROM PERFUMES WHERE ID = ?",
                JDBCPerfumeRepository::mapRow,
                id);
        if (perfume != null) {
            loadNotes(perfume);
            loadBrand(perfume);
        }
        return perfume;
    }

    private void loadNotes(Perfume perfume) {
        List<Note> notes = jdbcTemplate.query("SELECT * FROM NOTES WHERE ID IN " +
                        "(SELECT NOTE_ID FROM PERFUME_NOTES WHERE PERFUME_ID = ?) ",
                JDBCNoteRepository::mapRow,
                perfume.getId());
        perfume.setNotes(notes);
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

    public static Perfume mapRow(ResultSet rs, int i) throws SQLException {
        Perfume newperf =  new Perfume(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getDate("release_date").toLocalDate(),
                PerfumeType.valueOf(rs.getString("type")),
                rs.getDouble("price")
        );
        return newperf;
    }

}
