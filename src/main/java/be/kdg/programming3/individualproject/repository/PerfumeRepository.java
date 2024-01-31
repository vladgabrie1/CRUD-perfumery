package be.kdg.programming3.individualproject.repository;

import be.kdg.programming3.individualproject.domain.Perfume;

import java.time.LocalDate;
import java.util.List;

public interface PerfumeRepository {
    List<Perfume> findAll();
    Perfume findById(int id);
    Perfume create(Perfume perfume);
    void update(Perfume perfume);
    void delete(int id);
    List<Perfume> getPerfumesByPriceRangeAndReleaseDateAfter(Double minPrice, Double maxPrice, LocalDate releaseDate);
    List<Perfume> getPerfumesByPriceRange(Double minPrice, Double maxPrice);
}
