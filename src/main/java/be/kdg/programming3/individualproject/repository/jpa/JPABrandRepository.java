package be.kdg.programming3.individualproject.repository.jpa;

import be.kdg.programming3.individualproject.domain.Brand;
import be.kdg.programming3.individualproject.repository.BrandRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("prod")
public class JPABrandRepository implements BrandRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Brand> findAll() {
        logger.info("Retrieving all brands using JPA");
        List<Brand> brands = em.createQuery("SELECT b FROM Brand b", Brand.class).getResultList();
        logger.debug("Number of brands retrieved: {}", brands.size());
        return brands;
    }

    @Override
    public Brand create(Brand brand) {
        logger.info("Creating brand: {}", brand);
        em.getTransaction().begin();
        em.persist(brand);
        em.getTransaction().commit();
        logger.debug("Brand created successfully");
        return brand;
    }

    @Override
    public void update(Brand brand) {
        logger.info("Updating brand: {}", brand);
        em.getTransaction().begin();
        em.merge(brand);
        em.getTransaction().commit();
        logger.debug("Brand updated successfully");
    }

    @Override
    public void delete(int id) {
        logger.info("Deleting brand with ID: {}", id);
        Brand brand = em.find(Brand.class, id);
        em.getTransaction().begin();
        em.remove(brand);
        em.getTransaction().commit();
        logger.debug("Brand deleted successfully");
    }

    @Override
    public Brand findById(int id) {
        logger.info("Retrieving brand by ID: {}", id);
        Brand brand = em.find(Brand.class, id);
        if (brand == null) {
            logger.warn("Brand with ID {} not found", id);
        }
        return brand;
    }
}
