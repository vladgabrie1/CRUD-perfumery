package be.kdg.programming3.individualproject.service;

import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.repository.PerfumeRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Profile("!springdata")
public class DFPerfumeServiceIMPL implements PerfumeService{
    private PerfumeRepository perfumeRepository;
    JsonWriter writer;

    public DFPerfumeServiceIMPL(PerfumeRepository perfumeRepository, JsonWriter writer) {
        this.perfumeRepository = perfumeRepository;
        this.writer = writer;
    }

    @Override
    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    @Override
    public List<Perfume> getPerfumesByBrandOrYear(String brandName, String yearInput) {
        List<Perfume> allPerfumes = perfumeRepository.findAll();
        List<Perfume> filteredPerfumes = new ArrayList<>();

        for (Perfume perfume : allPerfumes) {
            boolean brandCriteria = brandName.isEmpty() || perfume.getBrand().getName().equalsIgnoreCase(brandName);
            boolean yearCriteria = yearInput.isEmpty() || (perfume.getReleaseDate().getYear() + 1900 == Integer.parseInt(yearInput));

            if (brandCriteria && yearCriteria) {
                filteredPerfumes.add(perfume);
            }
        }

        return filteredPerfumes;

    }

    @Override
    public void writePerfumesToJson(List<Perfume> perfumes) {
        writer.writePerfumes(perfumes);
    }

    @Override
    public void savePerfume(Perfume perfume) {
        perfumeRepository.create(perfume);
    }

    @Override
    public Perfume getPerfumeById(int id) {
        return perfumeRepository.findById(id);
    }

    @Override
    public void deletePerfume(int id) {
        perfumeRepository.delete(id);
    }

    @Override
    public List<Perfume> getPerfumesReleasedAfter(LocalDate releaseDate) {
        return null;
    }

    @Override
    public List<Perfume> getPerfumesByPriceRange(Double minPrice, Double maxPrice) {
        return perfumeRepository.getPerfumesByPriceRange(minPrice, maxPrice);
    }

    @Override
    public List<Perfume> getPerfumesByPriceRangeAndReleaseDateAfter(Double minPrice, Double maxPrice, LocalDate releaseDate) {
        return perfumeRepository.getPerfumesByPriceRangeAndReleaseDateAfter(minPrice, maxPrice, releaseDate);
    }
}
