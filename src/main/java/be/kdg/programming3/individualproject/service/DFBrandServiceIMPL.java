package be.kdg.programming3.individualproject.service;

import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.repository.BrandRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("!springdata")
public class DFBrandServiceIMPL implements BrandService{
    BrandRepository brandRepository;

    public DFBrandServiceIMPL(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getBrandById(int id) {
        return brandRepository.findAll().stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }
}
