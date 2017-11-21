package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.AuctionDto;

/**
 * Created by Łukasz on 21.11.2017.
 */
public interface AuctionService {
    AuctionDto getOne(String id);
}
