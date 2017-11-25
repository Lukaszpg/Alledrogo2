package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.model.Auction;

import java.util.List;

/**
 * Created by Łukasz on 20.11.2017.
 */
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findTop10ByHasEndedIsFalse();
    Auction findByUsersObserving_Id(Long id);
}
