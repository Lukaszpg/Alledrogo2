package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pro.lukasgorny.model.Rating;

/**
 * Created by lukaszgo on 2018-01-17.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
