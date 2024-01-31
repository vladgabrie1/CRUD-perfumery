package be.kdg.programming3.individualproject.presentation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.service.BrandService;

@Component
public class BrandConverter implements Converter<String, Brand> {
    private final BrandService brandService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public BrandConverter(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public Brand convert(String brandId) {
        try {
            if(brandId == null){
                logger.error("Brand is null");
            }
            int id = Integer.parseInt(brandId);
            return brandService.getBrandById(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid brandId");
        }
    }
}


