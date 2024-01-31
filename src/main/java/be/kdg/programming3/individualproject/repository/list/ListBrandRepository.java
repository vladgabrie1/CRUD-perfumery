package be.kdg.programming3.individualproject.repository.list;

import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.domain.Note;
import be.kdg.programming3.individualproject.repository.BrandRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("collections")
public class ListBrandRepository implements BrandRepository {
    List<Brand> brands = new ArrayList<Brand>();
    @Override
    public List<Brand> findAll() {
        return brands;
    }

    @Override
    public Brand create(Brand brand) {
        brand.setId(brands.stream().mapToInt(Brand::getId).max().orElse(0) + 1);
        brands.add(brand);
        return brand;
    }

    @Override
    public void update(Brand brand) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Brand findById(int id) {
        return null;
    }
}
