package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.BuyoutSaveDto;
import pro.lukasgorny.dto.auction.ObserveDto;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.AuctionRepository;
import pro.lukasgorny.repository.TransactionRepository;
import pro.lukasgorny.service.email.EmailSenderService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.MathHelper;

/**
 * Created by Łukasz on 24.11.2017.
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    private final TransactionRepository transactionRepository;
    private final AuctionRepository auctionRepository;
    private final GetAuctionService getAuctionService;
    private final UserService userService;
    private final EmailSenderService emailSenderService;
    private final MessageSource messages;
    private final GetTransactionService getTransactionService;

    @Autowired
    public AuctionServiceImpl(TransactionRepository transactionRepository, AuctionRepository auctionRepository,
                              GetAuctionService getAuctionService, UserService userService,
                              EmailSenderService emailSenderService, MessageSource messages, GetTransactionService getTransactionService) {
        this.transactionRepository = transactionRepository;
        this.auctionRepository = auctionRepository;
        this.getAuctionService = getAuctionService;
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.messages = messages;
        this.getTransactionService = getTransactionService;
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

        if (isBidPriceHigherOrEqualToAuctionMinimalPrice(auction)) {
            sendEmailNotificationToWinner(auction);
        } else {
            Transaction winningBid = getTransactionService.getWinningBidEntityForAuction(auction.getId());
            winningBid.setOfferAccepted(false);
            transactionRepository.save(winningBid);
        }

        auctionRepository.save(auction);
    }

    private boolean isBidPriceHigherOrEqualToAuctionMinimalPrice(Auction auction) {
        Transaction winningBid = getTransactionService.getWinningBidEntityForAuction(auction.getId());
        return MathHelper.bigDecimalLessOrEqualToFirstValue(auction.getBidMinimalPrice(), winningBid.getOffer());
    }

    private void sendEmailNotificationToWinner(Auction auction) {
        Transaction winningBid = getTransactionService.getWinningBidEntityForAuction(auction.getId());
        User winner = userService.getById(winningBid.getUser().getId());
        User seller = userService.getById(auction.getSeller().getId());

        String recipientAddress = winner.getEmail();
        String subject = messages.getMessage("email.auction.win.title", null, LocaleContextHolder.getLocale()) + " " + auction.getTitle();
        String message = messages.getMessage("email.auction.win.message", null, LocaleContextHolder.getLocale()) + " " + auction.getTitle() + " " +
                messages.getMessage("email.auction.win.user.part", null, LocaleContextHolder.getLocale()) + " " + seller.getEmail();
        emailSenderService.sendEmail(recipientAddress, subject, message);
    }

    @Override
    public Boolean checkHasAuctionEnded(String auctionId) {
        return getAuctionService.getOne(auctionId).getHasEnded();
    }

    @Override
    public void updateCurrentItemsAmountOrEndAuction(BuyoutSaveDto buyoutSaveDto) {
        Auction auction = getAuctionService.getOneEntity(buyoutSaveDto.getAuctionId());
        if ((auction.getCurrentAmount() - buyoutSaveDto.getAmountToBuy()) == 0) {
            endAuction(auction);
        } else {
            auction.setCurrentAmount(auction.getCurrentAmount() - buyoutSaveDto.getAmountToBuy());
            auctionRepository.save(auction);
        }
    }
}
