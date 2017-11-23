package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.BidDto;
import pro.lukasgorny.model.Bid;

/**
 * Created by Łukasz on 23.11.2017.
 */
public interface BidService {
    Boolean checkIsBiddingUserAuctionCreator();
    Boolean checkOfferLowerThanCurrentPrice();
    Bid getOneByAuctionIdAndWinningIsTrue(String id);
    void setBidDto(BidDto bidDto);
    void createBid();
}
