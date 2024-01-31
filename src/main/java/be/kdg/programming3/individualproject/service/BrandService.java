package be.kdg.programming3.individualproject.service;

import be.kdg.programming3.individualproject.domain.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand getBrandById(int id);
}
