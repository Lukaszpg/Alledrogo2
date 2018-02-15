package pro.lukasgorny.service.auction;

import org.springframework.http.ResponseEntity;

import pro.lukasgorny.dto.auction.ObserveResponseDto;
import pro.lukasgorny.dto.auction.BuyoutSaveDto;
import pro.lukasgorny.dto.auction.ObserveDto;
import pro.lukasgorny.model.Auction;

/**
 * Created by Łukasz on 24.11.2017.
 */
public interface AuctionService {
    ResponseEntity<ObserveResponseDto> observe(ObserveDto observeDto);
    Boolean unobserve(ObserveDto observeDto);
    Boolean isObserving(ObserveDto observeDto);
    void endAuction(Auction auction);
    Boolean checkIsUserAuctionCreator(String auctionId, String username);
    Boolean checkHasAuctionEnded(String id);
    void updateCurrentItemsAmountOrEndAuction(BuyoutSaveDto buyoutSaveDto);
}
