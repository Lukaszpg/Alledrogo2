package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.BidDto;
import pro.lukasgorny.model.Bid;

/**
 * Created by Łukasz on 23.11.2017.
 */
public interface CreateBidService {
    Boolean checkOfferLowerThanCurrentPrice();
    Boolean checkHasAuctionEnded();
    Bid getOneByAuctionIdAndWinningIsTrue(String id);
    void setBidDto(BidDto bidDto);
    void createBid();
}
