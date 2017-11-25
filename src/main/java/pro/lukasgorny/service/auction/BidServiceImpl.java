package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.BidDto;
import pro.lukasgorny.model.Bid;
import pro.lukasgorny.repository.BidRepository;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.MathHelper;

/**
 * Created by Łukasz on 23.11.2017.
 */
@Service
public class BidServiceImpl implements BidService {

    private final HashService hashService;
    private final BidRepository bidRepository;
    private final GetAuctionService getAuctionService;
    private final UserService userService;
    private final AuctionService auctionService;

    private BidDto bidDto;

    @Autowired
    public BidServiceImpl(HashService hashService, BidRepository bidRepository,
                          GetAuctionService getAuctionService, UserService userService, AuctionService auctionService) {
        this.hashService = hashService;
        this.bidRepository = bidRepository;
        this.getAuctionService = getAuctionService;
        this.userService = userService;
        this.auctionService = auctionService;
    }

    @Override
    public Boolean checkOfferLowerThanCurrentPrice() {
        if (bidDto.getAmount() != null) {
            Bid bid = getOneByAuctionIdAndWinningIsTrue(bidDto.getAuctionId());

            if (bid != null) {
                return MathHelper.bigDecimalLessOrEqualToFirstValue(bid.getAmount(), bidDto.getAmount());
            } else {
                AuctionResultDto auction = getAuctionService.getOne(bidDto.getAuctionId());
                return MathHelper.bigDecimalLessOrEqualToFirstValue(auction.getBidStartingPrice(), bidDto.getAmount());
            }
        }

        return false;
    }

    @Override
    public Boolean checkHasAuctionEnded() {
        return getAuctionService.getOne(bidDto.getAuctionId()).getHasEnded();
    }

    public void setBidDto(BidDto bidDto) {
        this.bidDto = bidDto;
    }

    @Override
    public void createBid() {
        Bid bid = createEntityFromDto();
        setLastWinningBidToFalse();
        bidRepository.save(bid);
    }

    private Bid createEntityFromDto() {
        Bid bid = new Bid();
        bid.setAmount(bidDto.getAmount());
        bid.setAuction(getAuctionService.getOneEntity(bidDto.getAuctionId()));
        bid.setWinning(true);
        bid.setUser(userService.getByEmail(bidDto.getUsername()));

        return bid;
    }

    private void setLastWinningBidToFalse() {
        Bid bid = getOneByAuctionIdAndWinningIsTrue(bidDto.getAuctionId());

        if (bid != null) {
            bid.setWinning(false);
            bidRepository.save(bid);
        }
    }

    @Override
    public Bid getOneByAuctionIdAndWinningIsTrue(String id) {
        return bidRepository.findByAuctionIdAndWinningIsTrue(hashService.decode(id));
    }
}
