package be.kdg.programming3.individualproject.service;


import be.kdg.programming3.individualproject.domain.Perfume;

import java.time.LocalDate;
import java.util.List;

public interface PerfumeService {
    List<Perfume> getAllPerfumes();
    List<Perfume> getPerfumesByBrandOrYear(String brandName, String yearInput);
    void writePerfumesToJson(List<Perfume> perfumes);
    void savePerfume(Perfume perfume);
    Perfume getPerfumeById(int id);
    void deletePerfume(int id);
    List<Perfume> getPerfumesReleasedAfter(LocalDate releaseDate);
    List<Perfume> getPerfumesByPriceRange(Double minPrice, Double maxPrice);
    List<Perfume> getPerfumesByPriceRangeAndReleaseDateAfter(Double minPrice, Double maxPrice,LocalDate releaseDate );

}
