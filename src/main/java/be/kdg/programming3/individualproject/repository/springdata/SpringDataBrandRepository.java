package be.kdg.programming3.individualproject.repository.springdata;

import be.kdg.programming3.individualproject.domain.Brand;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("springdata")
public interface SpringDataBrandRepository extends JpaRepository<Brand, Integer> {

}
