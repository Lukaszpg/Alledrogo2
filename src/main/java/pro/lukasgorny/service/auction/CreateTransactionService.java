package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.BidSaveDto;
import pro.lukasgorny.dto.auction.BuyoutSaveDto;
import pro.lukasgorny.model.Transaction;

/**
 * Created by Łukasz on 23.11.2017.
 */
public interface CreateTransactionService {
    Boolean checkOfferLowerThanCurrentPrice(BidSaveDto bidSaveDto);
    Transaction getOneByAuctionIdAndWinningIsTrue(String id);
    void createTransaction(BidSaveDto bidSaveDto);
    String createTransaction(BuyoutSaveDto buyoutSaveDto);
    void save(Transaction transaction);
}
