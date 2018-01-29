package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.SearchDto;
import pro.lukasgorny.model.Auction;

import java.util.List;

/**
 * Created by Łukasz on 21.11.2017.
 */
public interface GetAuctionService {
    List<AuctionResultDto> getByCategoryIdAndTitle(SearchDto searchDto);
    List<AuctionResultDto> getTopAuctions();
    AuctionResultDto getOne(String id);
    AuctionResultDto getOne(Long id);
    Auction getOneEntity(String id);
    Boolean auctionExists(String id);
    List<AuctionResultDto> getAllObservedByUserId(Long id);
    List<Auction> getAllAuctionsToEnd();
    Integer getAuctionCurrentItemAmount(String id);
}
