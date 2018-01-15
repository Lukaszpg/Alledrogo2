package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.ObserveDto;
import pro.lukasgorny.model.Auction;

/**
 * Created by Łukasz on 24.11.2017.
 */
public interface AuctionService {
    Boolean observe(ObserveDto observeDto);
    Boolean unobserve(ObserveDto observeDto);
    Boolean isObserving(ObserveDto observeDto);
    void endAuction(Auction auction);
    Boolean checkIsBiddingUserAuctionCreator(String auctionId, String username);
    Boolean checkHasAuctionEnded(String id);
}
