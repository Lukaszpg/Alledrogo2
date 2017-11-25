package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.BidDto;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.Bid;
import pro.lukasgorny.repository.AuctionRepository;
import pro.lukasgorny.repository.BidRepository;
import pro.lukasgorny.service.hash.HashService;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz on 21.11.2017.
 */
@Service
public class GetAuctionServiceImpl implements GetAuctionService {

    private final HashService hashService;
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;

    @Autowired
    public GetAuctionServiceImpl(AuctionRepository auctionRepository, HashService hashService,
                                 BidRepository bidRepository) {
        this.hashService = hashService;
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
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
    public Auction getOneEntity(String id) {
        return auctionRepository.findOne(hashService.decode(id));
    }

    @Override
    public Boolean auctionExists(String id) {
        return getOne(id) != null;
    }

    @Override
    public Auction getObservingByUserId(Long id) {
        return auctionRepository.findByUsersObserving_Id(id);
    }

    private List<AuctionResultDto> createDtoListFromEntityList(List<Auction> auctions) {
        List<AuctionResultDto> results = new ArrayList<>();
        if (auctions != null && !auctions.isEmpty()) {
            auctions.forEach(auction -> results.add(createDtoFromEntity(auction)));
        }
        return results;
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
        auctionResultDto.setEndDate(parseDate(auction));
        auctionResultDto.setNickname(auction.getUser().getEmail());
        auctionResultDto.setWinningBid(getWinningBid(auctionResultDto.getId()));
        auctionResultDto.setHasEnded(auction.getHasEnded());

        return auctionResultDto;
    }

    private BidDto getWinningBid(String id) {
        Bid bid = bidRepository.findByAuctionIdAndWinningIsTrue(hashService.decode(id));

        if (bid != null) {
            BidDto bidDto = new BidDto();
            bidDto.setAmount(bid.getAmount());
            bidDto.setUsername(bid.getUser().getEmail());

            return bidDto;
        }

        return null;
    }

    private String parseDate(Auction auction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return auction.getEndDate().format(formatter);
    }
}
