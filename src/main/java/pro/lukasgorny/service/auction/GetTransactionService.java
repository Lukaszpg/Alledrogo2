package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.TransactionResultDto;

import java.util.List;

/**
 * Created by Łukasz on 15.01.2018.
 */
public interface GetTransactionService {
    TransactionResultDto getWinningBidForAuction(String id);
    List<TransactionResultDto> getAllBoughtItemsByUserEmail(String email);
    List<TransactionResultDto> getAllSoldItemsByUserEmail(String email);
    List<TransactionResultDto> getAllBoughtItemsWithoutRatingForBuyerByUserEmail(String email);
}
