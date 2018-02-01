package pro.lukasgorny.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import pro.lukasgorny.model.Photo;

/**
 * Created by lukaszgo on 2018-02-01.
 */
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByAuctionId(Long auctionId);
}
