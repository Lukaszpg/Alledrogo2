package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.AuctionResultDto;

/**
 * Created by Łukasz on 21.11.2017.
 */
public interface GetAuctionService {
    AuctionResultDto getOne(String id);
}
