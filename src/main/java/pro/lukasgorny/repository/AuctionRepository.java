package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.lukasgorny.model.Auction;

/**
 * Created by Łukasz on 20.11.2017.
 */
public interface AuctionRepository extends JpaRepository<Auction, Long> {
}
