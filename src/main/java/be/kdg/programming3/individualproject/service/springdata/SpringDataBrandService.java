package be.kdg.programming3.individualproject.service.springdata;

import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.repository.springdata.SpringDataBrandRepository;
import be.kdg.programming3.individualproject.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("springdata")
public class SpringDataBrandService implements BrandService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final SpringDataBrandRepository brandRepository;

    public SpringDataBrandService(SpringDataBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        logger.info("Retrieving all brands");
        List<Brand> brands = brandRepository.findAll(Sort.by("name"));
        logger.debug("Number of brands retrieved: {}", brands.size());
        return brands;
    }

    @Override
    public Brand getBrandById(int id) {
        logger.info("Retrieving brand by ID: {}", id);
        Brand brand = brandRepository.findById(id).orElse(null);
        if (brand == null) {
            logger.warn("Brand with ID {} not found", id);
        }
        return brand;
    }
}
