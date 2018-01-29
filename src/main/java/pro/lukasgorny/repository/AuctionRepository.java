package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.util.QueryBody;

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

    @Query(QueryBody.AuctionQuery.FIND_BY_CATEGORY_ID_AND_TITLE)
    List<Auction> findByCategoryIdAndTitle(@Param("title") String title, @Param("ids") List<Long> categoryIdList);

    @Query(QueryBody.AuctionQuery.FIND_CURRENT_ITEMS_AMOUNT)
    Integer findCurrentItemsAmountByAuctionId(@Param("id") Long id);
}
