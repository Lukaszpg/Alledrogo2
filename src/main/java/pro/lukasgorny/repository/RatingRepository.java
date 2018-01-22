package pro.lukasgorny.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pro.lukasgorny.model.Rating;
import pro.lukasgorny.util.QueryBody;

/**
 * Created by lukaszgo on 2018-01-17.
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    @Query(QueryBody.RatingQuery.FIND_ALL_RECEIVED_RATINGS_FOR_USER)
    List<Rating> findAllReceivedRatingsByUserId(@Param("userId") Long userId);

    @Query(QueryBody.RatingQuery.FIND_ALL_ISSUED_RATINGS_FOR_USER)
    List<Rating> findAllIssuedRatingsByUserId(@Param("userId") Long userId);
}
