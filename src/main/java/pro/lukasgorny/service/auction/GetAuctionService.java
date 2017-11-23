package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.model.Auction;

/**
 * Created by Łukasz on 21.11.2017.
 */
public interface GetAuctionService {
    AuctionResultDto getOne(String id);
    Auction getOneEntity(String id);
    Boolean auctionExists(String id);
}
