package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.BidSaveDto;
import pro.lukasgorny.dto.auction.ObserveDto;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.AuctionRepository;
import pro.lukasgorny.service.user.UserService;

/**
 * Created by Łukasz on 24.11.2017.
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final GetAuctionService getAuctionService;
    private final UserService userService;

    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository, GetAuctionService getAuctionService, UserService userService) {
        this.auctionRepository = auctionRepository;
        this.getAuctionService = getAuctionService;
        this.userService = userService;
    }

    @Override
    public Boolean observe(ObserveDto observeDto) {
        if (!checkIsBiddingUserAuctionCreator(observeDto.getAuctionId(), observeDto.getUsername())) {
            Auction auction = getAuctionService.getOneEntity(observeDto.getAuctionId());
            User user = userService.getByEmail(observeDto.getUsername());
            auction.getUsersObserving().add(user);
            auctionRepository.save(auction);
            return true;
        }

        return false;
    }

    @Override
    public Boolean unobserve(ObserveDto observeDto) {
        Auction auction = getAuctionService.getOneEntity(observeDto.getAuctionId());
        User user = userService.getByEmail(observeDto.getUsername());
        auction.getUsersObserving().remove(user);
        auctionRepository.save(auction);
        return true;
    }

    @Override
    public Boolean isObserving(ObserveDto observeDto) {
        User user = userService.getByEmail(observeDto.getUsername());
        Auction auction = auctionRepository.findByUsersObserving_Id(user.getId());
        return auction != null;
    }

    @Override
    public Boolean checkIsBiddingUserAuctionCreator(String auctionId, String username) {
        AuctionResultDto auction = getAuctionService.getOne(auctionId);
        return auction.getSellerDto().getEmail().equals(username);
    }

    @Override
    public void endAuction(Auction auction) {
        auction.setHasEnded(true);
        auctionRepository.save(auction);
    }

    @Override
    public Boolean checkHasAuctionEnded(String auctionId) {
        return getAuctionService.getOne(auctionId).getHasEnded();
    }
}
