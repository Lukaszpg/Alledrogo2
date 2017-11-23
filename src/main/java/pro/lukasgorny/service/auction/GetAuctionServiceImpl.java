package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.repository.AuctionRepository;
import pro.lukasgorny.service.hash.HashService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Łukasz on 21.11.2017.
 */
@Service
public class GetAuctionServiceImpl implements GetAuctionService {

    private final HashService hashService;
    private final AuctionRepository auctionRepository;

    @Autowired
    public GetAuctionServiceImpl(AuctionRepository auctionRepository, HashService hashService) {
        this.hashService = hashService;
        this.auctionRepository = auctionRepository;
    }

    @Override
    public AuctionResultDto getOne(String id) {
        Auction auction = auctionRepository.findOne(hashService.decode(id));

        if(auction != null) {
            return createDtoFromEntity(auction);
        }

        return null;
    }

    private AuctionResultDto createDtoFromEntity(Auction auction) {
        AuctionResultDto auctionResultDto = new AuctionResultDto();
        auctionResultDto.setTitle(auction.getTitle());
        auctionResultDto.setEditorContent(auction.getEditorContent());
        auctionResultDto.setIsBuyout(auction.getBuyout());
        auctionResultDto.setIsBid(auction.getBid());
        auctionResultDto.setPrice(auction.getPrice());
        auctionResultDto.setAmount(auction.getAmount());
        auctionResultDto.setIsNew(auction.getNew());
        auctionResultDto.setCategory(auction.getCategory());
        auctionResultDto.setBidStartingPrice(auction.getBidStartingPrice());
        auctionResultDto.setEndDate(parseDate(auction));

        return auctionResultDto;
    }

    private String parseDate(Auction auction) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return auction.getEndDate().format(formatter);
    }
}
