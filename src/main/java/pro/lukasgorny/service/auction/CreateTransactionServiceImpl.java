package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.BidSaveDto;
import pro.lukasgorny.dto.auction.BuyoutSaveDto;
import pro.lukasgorny.enums.TransactionType;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.repository.TransactionRepository;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.MathHelper;

/**
 * Created by Łukasz on 23.11.2017.
 */
@Service
public class CreateTransactionServiceImpl implements CreateTransactionService {

    private final HashService hashService;
    private final TransactionRepository transactionRepository;
    private final GetAuctionService getAuctionService;
    private final UserService userService;
    private final AuctionService auctionService;

    @Autowired
    public CreateTransactionServiceImpl(HashService hashService, TransactionRepository transactionRepository, GetAuctionService getAuctionService,
            UserService userService, AuctionService auctionService) {
        this.hashService = hashService;
        this.transactionRepository = transactionRepository;
        this.getAuctionService = getAuctionService;
        this.userService = userService;
        this.auctionService = auctionService;
    }

    @Override
    public Boolean checkOfferLowerThanCurrentPrice(BidSaveDto bidSaveDto) {
        Transaction bid = getOneByAuctionIdAndWinningIsTrue(bidSaveDto.getAuctionId());

        if (bid != null) {
            return MathHelper.bigDecimalLessOrEqualToFirstValue(bid.getOffer(), bidSaveDto.getOfferedPrice());
        } else {
            AuctionResultDto auction = getAuctionService.getOne(bidSaveDto.getAuctionId());
            return MathHelper.bigDecimalLessOrEqualToFirstValue(auction.getBidStartingPrice(), bidSaveDto.getOfferedPrice());
        }
    }

    @Override
    public void createTransaction(BidSaveDto bidSaveDto) {
        Transaction bid = createEntityFromDto(bidSaveDto);
        setLastWinningBidToFalse(bidSaveDto);
        transactionRepository.save(bid);
    }

    @Override
    public void createTransaction(BuyoutSaveDto buyoutSaveDto) {
        Transaction buyout = createEntityFromDto(buyoutSaveDto);
        transactionRepository.save(buyout);
        updateAuctionCurrentItemsAmountOrEndAuction(buyoutSaveDto);
    }

    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    private Transaction createEntityFromDto(BidSaveDto bidSaveDto) {
        Transaction bid = new Transaction();
        bid.setOffer(bidSaveDto.getOfferedPrice());
        bid.setAuction(getAuctionService.getOneEntity(bidSaveDto.getAuctionId()));
        bid.setUser(userService.getByEmail(bidSaveDto.getUsername()));
        bid.setTransactionType(TransactionType.BID);
        bid.setIsWinning(bidSaveDto.getWinning() != null ? bidSaveDto.getWinning() : false);
        bid.setOfferAccepted(true);

        return bid;
    }

    private Transaction createEntityFromDto(BuyoutSaveDto buyoutSaveDto) {
        Transaction buyout = new Transaction();
        buyout.setAmountBought(buyoutSaveDto.getAmountToBuy());
        buyout.setAuction(getAuctionService.getOneEntity(buyoutSaveDto.getAuctionId()));
        buyout.setUser(userService.getByEmail(buyoutSaveDto.getUsername()));
        buyout.setTransactionType(TransactionType.BUYOUT);
        buyout.setIsWinning(null);
        return buyout;
    }

    private void setLastWinningBidToFalse(BidSaveDto bidSaveDto) {
        Transaction bid = getOneByAuctionIdAndWinningIsTrue(bidSaveDto.getAuctionId());

        if (bid != null) {
            bid.setIsWinning(false);
            transactionRepository.save(bid);
        }
    }

    private void updateAuctionCurrentItemsAmountOrEndAuction(BuyoutSaveDto buyoutSaveDto) {
        auctionService.updateCurrentItemsAmountOrEndAuction(buyoutSaveDto);
    }

    @Override
    public Transaction getOneByAuctionIdAndWinningIsTrue(String id) {
        return transactionRepository.findWinningBidForAuction(hashService.decode(id));
    }
}
