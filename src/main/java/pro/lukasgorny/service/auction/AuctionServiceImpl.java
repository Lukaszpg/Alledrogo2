package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.lukasgorny.dto.ObserveResponseDto;
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
    public ResponseEntity<ObserveResponseDto> observe(ObserveDto observeDto) {
        ObserveResponseDto observeResponseDto = new ObserveResponseDto();
        Auction auction = getAuctionService.getOneEntity(observeDto.getAuctionId());

        if (checkIsUserAuctionCreator(observeDto.getAuctionId(), observeDto.getUsername())) {
            observeResponseDto.setSuccess(false);
            observeResponseDto.setMessage(messages.getMessage("text.observe.owner", null, LocaleContextHolder.getLocale()));
            return new ResponseEntity<>(observeResponseDto, HttpStatus.OK);
        } else if(auction.getHasEnded()) {
            observeResponseDto.setSuccess(false);
            observeResponseDto.setMessage(messages.getMessage("text.observe.auction.ended", null, LocaleContextHolder.getLocale()));
            return new ResponseEntity<>(observeResponseDto, HttpStatus.OK);
        }

        User user = userService.getByEmail(observeDto.getUsername());
        auction.getUsersObserving().add(user);
        auctionRepository.save(auction);
        observeResponseDto.setSuccess(true);
        observeResponseDto.setMessage(messages.getMessage("text.observe.auction.success", null, LocaleContextHolder.getLocale()));

        return new ResponseEntity<>(observeResponseDto, HttpStatus.OK);
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
    public Boolean checkIsUserAuctionCreator(String auctionId, String username) {
        AuctionResultDto auction = getAuctionService.getOne(auctionId);
        return auction.getSellerDto().getEmail().equals(username);
    }

    @Override
    @Transactional
    public void endAuction(Auction auction) {
        auction.setHasEnded(true);

        if(auction.getBid() && isBidPriceHigherOrEqualToAuctionMinimalPrice(auction)) {
            sendEmailNotificationToWinner(auction);
        } else {
            Transaction winningBid = getTransactionService.getWinningBidEntityForAuction(auction.getId());

            if(winningBid != null) {
                sendEmailNotificationToWinnerMinimalPriceNotMet(auction);
                winningBid.setOfferAccepted(false);
                transactionRepository.save(winningBid);
            }
        }

        auctionRepository.save(auction);
    }

    private boolean isBidPriceHigherOrEqualToAuctionMinimalPrice(Auction auction) {
        Transaction winningBid = getTransactionService.getWinningBidEntityForAuction(auction.getId());
        return MathHelper.bigDecimalHigherOrEqualToFirstValue(auction.getBidMinimalPrice(), winningBid.getOffer());
    }

    private void sendEmailNotificationToWinner(Auction auction) {
        Transaction winningBid = getTransactionService.getWinningBidEntityForAuction(auction.getId());
        User winner = userService.getById(winningBid.getUser().getId());
        User seller = userService.getById(auction.getSeller().getId());

        String recipientAddress = winner.getEmail();
        String subject = String.format(messages.getMessage("email.auction.win.title", null, LocaleContextHolder.getLocale()), auction.getTitle());
        String message = String.format(messages.getMessage("email.auction.not.minimal.price.message", null, LocaleContextHolder.getLocale()), auction.getTitle(), seller.getEmail());
        emailSenderService.sendEmail(recipientAddress, subject, message);
    }


    private void sendEmailNotificationToWinnerMinimalPriceNotMet(Auction auction) {
        Transaction winningBid = getTransactionService.getWinningBidEntityForAuction(auction.getId());
        User winner = userService.getById(winningBid.getUser().getId());

        String recipientAddress = winner.getEmail();
        String subject = String.format(messages.getMessage("email.auction.not.minimal.price.title", null, LocaleContextHolder.getLocale()), auction.getTitle());
        String message = String.format(messages.getMessage("email.auction.not.minimal.price.message", null, LocaleContextHolder.getLocale()), auction.getTitle());

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
