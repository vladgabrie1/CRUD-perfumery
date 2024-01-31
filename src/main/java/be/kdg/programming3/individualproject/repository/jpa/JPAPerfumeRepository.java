package be.kdg.programming3.individualproject.repository.jpa;

import be.kdg.programming3.individualproject.domain.Perfume;
import be.kdg.programming3.individualproject.repository.PerfumeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("prod")
public class JPAPerfumeRepository implements PerfumeRepository {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Perfume> findAll() {
        logger.info("Retrieving all perfumes using JPA");
        List<Perfume> perfumes = em.createQuery("SELECT p FROM Perfume p", Perfume.class).getResultList();
        logger.debug("Number of perfumes retrieved: {}", perfumes.size());
        return perfumes;
    }

    @Override
    public Perfume findById(int id) {
        logger.info("Retrieving perfume by ID: {}", id);
        Perfume perfume = em.find(Perfume.class, id);
        if (perfume == null) {
            logger.warn("Perfume with ID {} not found", id);
        }
        return perfume;
    }

    @Override
    public List<Perfume> getPerfumesByPriceRange(Double minPrice, Double maxPrice) {
        logger.info("Retrieving perfumes by price range: {} - {}", minPrice, maxPrice);
        return em.createQuery("SELECT p FROM Perfume p WHERE p.price BETWEEN :min AND :max", Perfume.class)
                .setParameter("min", minPrice)
                .setParameter("max", maxPrice)
                .getResultList();
    }

    @Override
    @Transactional
    public Perfume create(Perfume perfume) {
        logger.info("Creating perfume: {}", perfume);
        em.persist(perfume);
        logger.debug("Perfume created successfully");
        return perfume;
    }

    @Override
    @Transactional
    public void update(Perfume perfume) {
        logger.info("Updating perfume: {}", perfume);
        em.getTransaction().begin();
        em.merge(perfume);
        em.getTransaction().commit();
        logger.debug("Perfume updated successfully");
    }

    @Override
    public List<Perfume> getPerfumesByPriceRangeAndReleaseDateAfter(Double minPrice, Double maxPrice, LocalDate releaseDate) {
        logger.info("Retrieving perfumes by price range: {} - {} and release date after: {}", minPrice, maxPrice, releaseDate);
        String jpql = "SELECT p FROM Perfume p WHERE p.price BETWEEN :minPrice AND :maxPrice AND p.releaseDate > :releaseDate";
        return em.createQuery(jpql, Perfume.class)
                .setParameter("minPrice", minPrice)
                .setParameter("maxPrice", maxPrice)
                .setParameter("releaseDate", releaseDate)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(int id) {
        logger.info("Deleting perfume with ID: {}", id);
        Perfume perfume = em.find(Perfume.class, id);
        em.remove(perfume);
        logger.debug("Perfume deleted successfully");
    }
}
