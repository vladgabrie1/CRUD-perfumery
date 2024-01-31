package be.kdg.programming3.individualproject.repository.jdbc;

import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.repository.BrandRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Profile("dev")
public class JDBCBrandRepository implements BrandRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert brandInserter;

    public JDBCBrandRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        brandInserter = new SimpleJdbcInsert(jdbcTemplate).withTableName("BRANDS")
                .usingGeneratedKeyColumns("ID");
    }

    @Override
    public List<Brand> findAll() {
        return jdbcTemplate.query("SELECT * FROM BRANDS",
                JDBCBrandRepository::mapRow);
    }

    @Override
    public Brand create(Brand brand) {
        int brandID = brandInserter.executeAndReturnKey(Map.of(
                "NAME", brand.getName()
        )).intValue();
        brand.setId(brandID);
        return brand;
    }

    @Override
    public void update(Brand brand) {
        String sql = "UPDATE BRANDS SET NAME = :name WHERE ID = :id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", brand.getName());
        parameters.put("id", brand.getId());

       jdbcTemplate.update(sql, parameters);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM BRANDS WHERE ID = :id";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);

        jdbcTemplate.update(sql, parameters);
    }

    @Override
    public Brand findById(int id) {
        String sql = "SELECT * FROM BRANDS WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, JDBCBrandRepository::mapRow, id);

    }

    public static Brand mapRow(ResultSet rs, int i) throws SQLException {
        return new Brand(
                rs.getInt("id"),
                rs.getString("name"));
    }
}
