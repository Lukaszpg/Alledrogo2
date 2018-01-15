package pro.lukasgorny.service.auction;

import pro.lukasgorny.dto.auction.TransactionResultDto;

/**
 * Created by Łukasz on 15.01.2018.
 */
public interface GetTransactionService {
    TransactionResultDto getWinningBidForAuction(String id);
}
