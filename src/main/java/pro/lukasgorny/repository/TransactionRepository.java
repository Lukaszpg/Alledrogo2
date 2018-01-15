package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.util.QueryBody;

/**
 * Created by Łukasz on 12.01.2018.
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query(QueryBody.TransactionQuery.FIND_WINNING_BID_FOR_AUCTION)
    Transaction findWinningBidForAuction(@Param("id") Long id);
}
