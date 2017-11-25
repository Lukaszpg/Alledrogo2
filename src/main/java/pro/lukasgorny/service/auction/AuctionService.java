package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.ObserveDto;

/**
 * Created by Łukasz on 24.11.2017.
 */
public interface AuctionService {
    Boolean observe();

    Boolean unobserve();

    Boolean isObserving();

    void setObserveDto(ObserveDto observeDto);

    Boolean checkIsBiddingUserAuctionCreator(String auctionId, String username);
}
