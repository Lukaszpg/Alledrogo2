package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.lukasgorny.model.Bid;

/**
 * Created by Łukasz on 23.11.2017.
 */
public interface BidRepository extends JpaRepository<Bid, Long> {
    Bid findByAuctionIdAndWinningIsTrue(Long id);
}
