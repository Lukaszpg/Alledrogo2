package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.auction.TransactionResultDto;
import pro.lukasgorny.enums.TransactionType;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.TransactionRepository;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.DateFormatter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 15.01.2018.
 */
@Service
public class GetTransactionServiceImpl implements GetTransactionService {

    private final TransactionRepository transactionRepository;
    private final HashService hashService;
    private final UserService userService;
    private final GetAuctionService getAuctionService;

    @Autowired
    public GetTransactionServiceImpl(TransactionRepository transactionRepository, HashService hashService,
                                     UserService userService, @Lazy GetAuctionService getAuctionService) {
        this.transactionRepository = transactionRepository;
        this.hashService = hashService;
        this.userService = userService;
        this.getAuctionService = getAuctionService;
    }

    @Override
    public Transaction getOneEntity(String id) {
        return transactionRepository.getOne(hashService.decode(id));
    }

    @Override
    public TransactionResultDto getWinningBidForAuction(String id) {
        Transaction bid = transactionRepository.findWinningBidForAuction(hashService.decode(id));

        if (bid != null) {
            return createDtoFromEntity(bid);
        }

        return null;
    }

    @Override
    public List<TransactionResultDto> getAllBoughtItemsByUserEmail(String email) {
        User user = userService.getByEmail(email);
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(transactionRepository.findAllEndedBuyoutsForUserBuyer(user.getId()));
        transactions.addAll(transactionRepository.findAllEndedBidsForUserBuyer(user.getId()));
        return createDtoListFromEntityList(transactions);
    }

    @Override
    public List<TransactionResultDto> getAllSoldItemsByUserEmail(String email) {
        User user = userService.getByEmail(email);
        List<Transaction> transactions = new ArrayList<>();
        transactions.addAll(transactionRepository.findAllEndedBuyoutsForUserSeller(user.getId()));
        transactions.addAll(transactionRepository.findAllEndedBidsForUserSeller(user.getId()));
        return createDtoListFromEntityList(transactions);
    }

    @Override
    public List<TransactionResultDto> getAllBoughtItemsWithoutRatingForBuyerByUserEmail(String email) {
        User user = userService.getByEmail(email);
        return createDtoListFromEntityList(transactionRepository.findAllBoughtItemsWithoutRatingForBuyer(user.getId()));
    }

    @Override
    public List<TransactionResultDto> getAllSoldItemsWithoutRatingForSellerByUserEmail(String email) {
        User user = userService.getByEmail(email);
        return createDtoListFromEntityList(transactionRepository.findAllSoldItemsWithoutRatingForSeller(user.getId()));
    }

    private List<TransactionResultDto> createDtoListFromEntityList(List<Transaction> transactions) {
        return transactions != null ? transactions.stream().map(this::createDtoFromEntity).collect(Collectors.toList()) : new ArrayList<>();
    }

    private TransactionResultDto createDtoFromEntity(Transaction transaction) {
        Auction auction = transaction.getAuction();
        TransactionResultDto transactionResultDto = new TransactionResultDto();
        transactionResultDto.setId(hashService.encode(transaction.getId()));
        transactionResultDto.setPrice(transaction.getOffer());
        transactionResultDto.setBuyerName(transaction.getUser().getEmail());
        transactionResultDto.setBuyerDetails(userService.getUserData(transaction.getUser().getEmail()));
        transactionResultDto.setTransactionType(transaction.getTransactionType());
        transactionResultDto.setCreated(DateFormatter.formatDateToHourMinuteFormat(transaction.getCreateDate()));
        transactionResultDto.setAmountBought(transaction.getAmountBought());
        transactionResultDto.setPrice(calculatePrice(transaction));
        transactionResultDto.setAuctionTitle(auction.getTitle());
        transactionResultDto.setSellerDto(userService.createDtoFromEntity(auction.getSeller()));
        return transactionResultDto;
    }

    private BigDecimal calculatePrice(Transaction transaction) {
        if (TransactionType.BID.equals(transaction.getTransactionType())) {
            return transaction.getOffer();
        } else {
            return transaction.getAuction().getPrice().multiply(new BigDecimal(transaction.getAmountBought()));
        }
    }
}
