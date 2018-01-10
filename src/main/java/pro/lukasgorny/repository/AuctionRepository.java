package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.model.Auction;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Łukasz on 20.11.2017.
 */
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findTop10ByHasEndedIsFalse();
    Auction findByUsersObserving_Id(Long id);
    List<Auction> findAllByUsersObserving_Id(Long id);
    List<Auction> findAllByHasEndedIsFalseAndEndDateBefore(LocalDateTime dateTime);

    @Query("SELECT a FROM Auction a INNER JOIN a.bids b WHERE a.hasEnded = true AND b.isWinning = false AND b.user.id = :userId")
    List<Auction> findEndedNotWonAuctionsForUser(Long userId);
}
