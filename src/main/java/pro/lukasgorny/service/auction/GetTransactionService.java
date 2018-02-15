package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.TransactionResultDto;
import pro.lukasgorny.model.Transaction;

import java.util.List;

/**
 * Created by Łukasz on 15.01.2018.
 */
public interface GetTransactionService {
    Transaction getOneEntity(String id);
    TransactionResultDto getWinningBidForAuction(String id);
    List<TransactionResultDto> getAllBoughtItemsByUserEmail(String email);
    List<TransactionResultDto> getAllSoldItemsByUserEmail(String email);
    List<TransactionResultDto> getAllBoughtItemsWithoutRatingForBuyerByUserEmail(String email);
    List<TransactionResultDto> getAllSoldItemsWithoutRatingForSellerByUserEmail(String email);
    Transaction getWinningBidEntityForAuction(Long id);
    boolean isUserBuyer(String username, String transactionId);
}
