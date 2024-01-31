package be.kdg.programming3.individualproject.repository;

import be.kdg.programming3.individualproject.domain.Brand;

import java.util.List;

public interface BrandRepository {
    List<Brand> findAll();
    Brand create(Brand brand);
    void update(Brand brand);
    void delete(int id);
    Brand findById(int id);
}
