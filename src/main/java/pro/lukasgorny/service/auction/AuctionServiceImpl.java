package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.auction.AuctionResultDto;
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

    private ObserveDto observeDto;

    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository, GetAuctionService getAuctionService, UserService userService) {
        this.auctionRepository = auctionRepository;
        this.getAuctionService = getAuctionService;
        this.userService = userService;
    }

    @Override
    public Boolean observe() {
        if(!checkIsBiddingUserAuctionCreator(observeDto.getAuctionId(), observeDto.getUsername())) {
            Auction auction = getAuctionService.getOneEntity(observeDto.getAuctionId());
            User user = userService.getByEmail(observeDto.getUsername());
            auction.getUsersObserving().add(user);
            auctionRepository.save(auction);
            return true;
        }

        return false;
    }

    @Override
    public Boolean unobserve() {
        Auction auction = getAuctionService.getOneEntity(observeDto.getAuctionId());
        User user = userService.getByEmail(observeDto.getUsername());
        auction.getUsersObserving().remove(user);
        auctionRepository.save(auction);
        return true;
    }

    @Override
    public Boolean isObserving() {
        User user = userService.getByEmail(observeDto.getUsername());
        Auction auction = auctionRepository.findByUsersObserving_Id(user.getId());
        return auction != null;
    }

    @Override
    public Boolean checkIsBiddingUserAuctionCreator(String auctionId, String username) {
        AuctionResultDto auction = getAuctionService.getOne(auctionId);
        return auction.getNickname().equals(username);
    }

    @Override
    public void setObserveDto(ObserveDto observeDto) {
        this.observeDto = observeDto;
    }
}
