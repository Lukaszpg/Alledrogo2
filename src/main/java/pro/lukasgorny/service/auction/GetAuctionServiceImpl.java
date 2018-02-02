package pro.lukasgorny.service.auction;

import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.SearchDto;
import pro.lukasgorny.dto.auction.TransactionResultDto;
import pro.lukasgorny.enums.RatingTypeEnum;
import pro.lukasgorny.enums.TransactionType;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.Photo;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.repository.AuctionRepository;
import pro.lukasgorny.service.category.GetCategoryService;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.CollectionHelper;
import pro.lukasgorny.util.DateFormatter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Łukasz on 21.11.2017.
 */
@Service
public class GetAuctionServiceImpl implements GetAuctionService {

    private final HashService hashService;
    private final AuctionRepository auctionRepository;
    private final GetTransactionService getTransactionService;
    private final GetCategoryService getCategoryService;
    private final UserService userService;
    private final PhotoService photoService;

    @Autowired
    public GetAuctionServiceImpl(AuctionRepository auctionRepository, HashService hashService, GetTransactionService getTransactionService,
            @Lazy GetCategoryService getCategoryService, UserService userService, PhotoService photoService) {
        this.hashService = hashService;
        this.auctionRepository = auctionRepository;
        this.getTransactionService = getTransactionService;
        this.getCategoryService = getCategoryService;
        this.userService = userService;
        this.photoService = photoService;
    }

    @Override
    public List<AuctionResultDto> getByCategoryIdAndTitle(SearchDto searchDto) {
        List<Long> categoryIds = prepareCategoryIds(searchDto);
        List<Auction> auctions = auctionRepository.findByCategoryIdAndTitle(searchDto.getSearchString(), categoryIds);
        return createDtoListFromEntityList(auctions);
    }

    @Override
    public Integer getAuctionsCountByCategory(Long id) {
        List<Long> ids = getCategoryService.getAllLeafIdsByTopCategoryId(id);
        return auctionRepository.findAuctionCountByCategoryId(ids);
    }

    @Override
    public List<AuctionResultDto> getTopAuctions() {
        List<Auction> auctions = auctionRepository.findTop10ByHasEndedIsFalse();
        return createDtoListFromEntityList(auctions);
    }

    @Override
    public AuctionResultDto getOne(String id) {
        Auction auction = auctionRepository.findOne(hashService.decode(id));

        if (auction != null) {
            return createDtoFromEntity(auction);
        }

        return null;
    }

    @Override
    public AuctionResultDto getOne(Long id) {
        Auction auction = auctionRepository.findOne(id);

        if (auction != null) {
            return createDtoFromEntity(auction);
        }

        return null;
    }

    @Override
    public Auction getOneEntity(String id) {
        return auctionRepository.findOne(hashService.decode(id));
    }

    @Override
    public Boolean auctionExists(String id) {
        return getOne(id) != null;
    }

    @Override
    public List<AuctionResultDto> getAllObservedByUserId(Long id) {
        List<Auction> auctions = auctionRepository.findAllByUsersObserving_Id(id);
        return createDtoListFromEntityList(auctions);
    }

    @Override
    public List<Auction> getAllAuctionsToEnd() {
        return auctionRepository.findAllByHasEndedIsFalseAndUntilOutOfItemsIsFalseAndEndDateBefore(LocalDateTime.now());
    }

    @Override
    public Integer getAuctionCurrentItemAmount(String id) {
        return auctionRepository.findCurrentItemsAmountByAuctionId(hashService.decode(id));
    }

    private List<Long> prepareCategoryIds(SearchDto searchDto) {
        if (StringUtils.isNullOrEmpty(searchDto.getCategoryId())) {
            return getCategoryService.getAllCategoryIds();
        } else {
            return getCategoryService.getAllLeafIdsByTopCategoryId(searchDto.getCategoryId());
        }
    }

    private List<Long> prepareCategoryIds(Long id) {
        return getCategoryService.getAllLeafIdsByTopCategoryId(id);
    }

    private List<AuctionResultDto> createDtoListFromEntityList(List<Auction> auctions) {
        return auctions != null ? auctions.stream().map(this::createDtoFromEntity).collect(Collectors.toList()) : new ArrayList<>();
    }

    private AuctionResultDto createDtoFromEntity(Auction auction) {
        AuctionResultDto auctionResultDto = new AuctionResultDto();
        auctionResultDto.setId(hashService.encode(auction.getId()));
        auctionResultDto.setTitle(auction.getTitle());
        auctionResultDto.setEditorContent(auction.getEditorContent());
        auctionResultDto.setIsBuyout(auction.getBuyout());
        auctionResultDto.setIsBid(auction.getBid());
        auctionResultDto.setPrice(auction.getPrice());
        auctionResultDto.setAmount(auction.getAmount());
        auctionResultDto.setIsNew(auction.getNew());
        auctionResultDto.setCategory(auction.getCategory());
        auctionResultDto.setBidStartingPrice(auction.getBidStartingPrice());
        auctionResultDto.setBidMinimalPrice(auction.getBidMinimalPrice());
        auctionResultDto.setEndDate(formatEndDateIfNotUntilOutOfItems(auction));
        auctionResultDto.setSellerDto(userService.createDtoFromEntity(auction.getSeller()));
        auctionResultDto.setWinningBid(getWinningBid(auctionResultDto.getId()));
        auctionResultDto.setHasEnded(auction.getHasEnded());
        auctionResultDto.setCurrentAmount(auction.getCurrentAmount());
        auctionResultDto.setBiddingUsersAmount(calculateBiddingUserAmount(auction));
        auctionResultDto.setMainImage(getMainImage(auction));
        auctionResultDto.setPositiveCommentPercent(calculatePositiveCommentPercent(auction));
        auctionResultDto.setUntilOufOfItems(auction.getUntilOutOfItems());
        auctionResultDto.setBuyoutUsersAmount(calculateBuyoutUserAmount(auction));

        return auctionResultDto;
    }

    private String formatEndDateIfNotUntilOutOfItems(Auction auction) {
        if (!auction.getUntilOutOfItems()) {
            return DateFormatter.formatDateToCountdownFormat(auction.getEndDate());
        }

        return null;
    }

    private Integer calculatePositiveCommentPercent(Auction auction) {
        Integer commentsAmount = auction.getTransactions().size();

        List<Transaction> transactionsWithBuyerRating = auction.getTransactions().stream().filter(transaction -> transaction.getBuyerRating() != null)
                                                               .collect(Collectors.toList());
        List<Transaction> positiveCommentsTransactions = transactionsWithBuyerRating.stream().filter(transaction -> RatingTypeEnum.POSITIVE
                .equals(transaction.getBuyerRating().getRatingTypeEnum())).collect(Collectors.toList());

        if (commentsAmount == 0 || positiveCommentsTransactions.isEmpty()) {
            return null;
        }

        Integer positiveCommentsAmount = positiveCommentsTransactions.size();

        return (positiveCommentsAmount * 100) / commentsAmount;
    }

    private String getMainImage(Auction auction) {
        List<Photo> mainPhoto = auction.getPhotos().stream().filter(Photo::getIsMain).collect(Collectors.toList());

        if (mainPhoto.size() > 0) {
            byte[] image = photoService.readImageFromDisk(mainPhoto.get(0).getStoragePath());
            return Base64.getEncoder().encodeToString(image);
        }

        return null;
    }

    private TransactionResultDto getWinningBid(String id) {
        return getTransactionService.getWinningBidForAuction(id);
    }

    private Integer calculateBiddingUserAmount(Auction auction) {
        List<Transaction> bids = auction.getTransactions().stream()
                                        .filter(transaction -> TransactionType.BID.equals(transaction.getTransactionType()))
                                        .collect(Collectors.toList());

        List<Transaction> distinctBids = bids.stream().filter(CollectionHelper.distinctByKey(a -> a.getUser().getId()))
                                        .collect(Collectors.toList());
        return distinctBids.size();
    }

    private Integer calculateBuyoutUserAmount(Auction auction) {
        List<Transaction> buyouts = auction.getTransactions().stream()
                                        .filter(transaction -> TransactionType.BUYOUT.equals(transaction.getTransactionType()))
                                        .collect(Collectors.toList());
        List<Transaction> distinctBuyouts = buyouts.stream().filter(CollectionHelper.distinctByKey(a -> a.getUser().getId()))
                                             .collect(Collectors.toList());
        return distinctBuyouts.size();
    }
}
