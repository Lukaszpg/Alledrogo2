package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.AuctionSaveDto;
import pro.lukasgorny.model.Auction;

/**
 * Created by Łukasz on 20.11.2017.
 */
public interface CreateAuctionService {
    Auction create(AuctionSaveDto auctionSaveDto);
}
