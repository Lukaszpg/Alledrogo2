package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.ObserveDto;
import pro.lukasgorny.model.Auction;

/**
 * Created by Łukasz on 24.11.2017.
 */
public interface AuctionService {
    Boolean observe();

    Boolean unobserve();

    Boolean isObserving();

    void setObserveDto(ObserveDto observeDto);

    void endAuction(Auction auction);

    Boolean checkIsBiddingUserAuctionCreator(String auctionId, String username);
}
