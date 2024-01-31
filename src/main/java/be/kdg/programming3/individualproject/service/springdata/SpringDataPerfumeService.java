package be.kdg.programming3.individualproject.service.springdata;

import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.exceptions.PerfumePriceException;
import be.kdg.programming3.individualproject.repository.springdata.SpringDataPerfumeRepository;
import be.kdg.programming3.individualproject.service.PerfumeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Profile("springdata")
public class SpringDataPerfumeService implements PerfumeService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SpringDataPerfumeRepository perfumeRepository;

    public SpringDataPerfumeService(SpringDataPerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    @Override
    public List<Perfume> getAllPerfumes() {
        logger.info("Retrieving all perfumes");
        List<Perfume> perfumes = perfumeRepository.findAll(Sort.by("name"));
        logger.debug("Number of perfumes retrieved: {}", perfumes.size());
        return perfumes;
    }

    @Override
    public List<Perfume> getPerfumesByBrandOrYear(String brandName, String yearInput) {
        return null;
    }

    @Override
    public void writePerfumesToJson(List<Perfume> perfumes) {
    }

    @Override
    public void savePerfume(Perfume perfume) {
        logger.info("Saving perfume: {}", perfume);

        // Check if the perfume price is reasonable
        if (perfume.getPrice() > 10000) {
            logger.error("Perfume price exceeds limit: {}", perfume.getPrice());
            throw new PerfumePriceException("The price " + perfume.getPrice() + " is unreasonable for perfume " + perfume.getName());
        }

        perfumeRepository.save(perfume);
        logger.debug("Perfume saved successfully");
    }

    @Override
    public Perfume getPerfumeById(int id) {
        logger.info("Retrieving perfume by ID: {}", id);
        Perfume perfume = perfumeRepository.findById(id).orElse(null);
        if (perfume == null) {
            logger.warn("Perfume with ID {} not found", id);
        }
        return perfume;
    }

    @Override
    public void deletePerfume(int id) {
        logger.warn("Deleting perfume with ID: {}", id);
        perfumeRepository.deleteById(id);
        logger.debug("Perfume deleted successfully");
    }

    @Override
    public List<Perfume> getPerfumesReleasedAfter(LocalDate releaseDate) {
        logger.info("Retrieving perfumes released after: {}", releaseDate);
        return perfumeRepository.findByReleaseDateAfter(releaseDate);
    }

    @Override
    public List<Perfume> getPerfumesByPriceRange(Double minPrice, Double maxPrice) {
        logger.info("Retrieving perfumes by price range: {} - {}", minPrice, maxPrice);
        return perfumeRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Perfume> getPerfumesByPriceRangeAndReleaseDateAfter(Double minPrice, Double maxPrice, LocalDate releaseDate) {
        logger.info("Retrieving perfumes by price range: {} - {} and release date after: {}", minPrice, maxPrice, releaseDate);
        return perfumeRepository.findByPriceBetweenAndReleaseDateAfter(minPrice, maxPrice, releaseDate);
    }
}
