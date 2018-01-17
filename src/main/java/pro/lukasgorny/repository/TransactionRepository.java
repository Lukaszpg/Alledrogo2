package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.util.QueryBody;

import java.util.List;

/**
 * Created by Łukasz on 12.01.2018.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(QueryBody.TransactionQuery.FIND_WINNING_BID_FOR_AUCTION)
    Transaction findWinningBidForAuction(@Param("id") Long id);

    @Query(QueryBody.TransactionQuery.FIND_ALL_ENDED_BUYOUTS_FOR_USER_BUYER)
    List<Transaction> findAllEndedBuyoutsForUserBuyer(@Param("userId") Long id);

    @Query(QueryBody.TransactionQuery.FIND_ALL_ENDED_BIDS_FOR_USER_BUYER)
    List<Transaction> findAllEndedBidsForUserBuyer(@Param("userId") Long id);

    @Query(QueryBody.TransactionQuery.FIND_ALL_ENDED_BUYOUTS_FOR_USER_SELLER)
    List<Transaction> findAllEndedBuyoutsForUserSeller(@Param("userId") Long id);

    @Query(QueryBody.TransactionQuery.FIND_ALL_ENDED_BIDS_FOR_USER_SELLER)
    List<Transaction> findAllEndedBidsForUserSeller(@Param("userId") Long id);

    @Query(QueryBody.TransactionQuery.FIND_ALL_BOUGHT_ITEMS_WITHOUT_RATING_FOR_BUYER)
    List<Transaction> findAllBoughtItemsWithoutRatingForBuyer(@Param("userId") Long id);

    @Query(QueryBody.TransactionQuery.FIND_ALL_SOLD_ITEMS_WITHOUT_RATING_FOR_BUYER)
    List<Transaction> findAllSoldItemsWithoutRatingForBuyer(@Param("userId") Long id);
}
