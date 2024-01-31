package be.kdg.programming3.individualproject.repository.springdata;

import be.kdg.programming3.individualproject.domain.Perfume;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Profile("springdata")
public interface SpringDataPerfumeRepository extends JpaRepository<Perfume, Integer> {
    List<Perfume> findByReleaseDateAfter(LocalDate localDate);
    List<Perfume> findByPriceBetween(double price, double price2);

    // Custom query to find perfumes by price range and released after a specific date
    @Query("SELECT p FROM Perfume p WHERE p.releaseDate > :releaseDate AND p.price BETWEEN :price AND :price2")
    List<Perfume> findByPriceBetweenAndReleaseDateAfter(@Param("price") double price,
                                                        @Param("price2") double price2,
                                                        @Param("releaseDate") LocalDate date);

}
