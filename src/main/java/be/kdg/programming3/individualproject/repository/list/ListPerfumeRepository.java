package be.kdg.programming3.individualproject.repository.list;

import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.repository.PerfumeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("collections")
public class ListPerfumeRepository implements PerfumeRepository {
    static final List<Perfume> perfumes = new ArrayList<>();
    @Override
    public List<Perfume> findAll() {
        return perfumes;
    }

    @Override
    public List<Perfume> getPerfumesByPriceRange(Double minPrice, Double maxPrice) {
        return null;
    }

    @Override
    public List<Perfume> getPerfumesByPriceRangeAndReleaseDateAfter(Double minPrice, Double maxPrice, LocalDate releaseDate) {
        return null;
    }

    @Override
    public Perfume create(Perfume perfume) {
        perfume.setId(perfumes.stream().mapToInt(Perfume::getId).max().orElse(0) + 1);
        perfumes.add(perfume);
        return perfume;
    }

    @Override
    public Perfume findById(int id) {
        return perfumes.stream().filter(perfume -> perfume.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void update(Perfume perfume) {

    }

    @Override
    public void delete(int id) {

    }
}
