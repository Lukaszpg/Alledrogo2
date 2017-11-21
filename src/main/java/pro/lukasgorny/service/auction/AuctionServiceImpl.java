package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.AuctionDto;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.repository.AuctionRepository;
import pro.lukasgorny.service.hash.HashService;

/**
 * Created by Łukasz on 21.11.2017.
 */
@Service
public class AuctionServiceImpl implements AuctionService {

    private final AuctionRepository auctionRepository;
    private final HashService hashService;

    @Autowired
    public AuctionServiceImpl(AuctionRepository auctionRepository, HashService hashService) {
        this.auctionRepository = auctionRepository;
        this.hashService = hashService;
    }

    @Override
    public AuctionDto getOne(String id) {
        Auction auction = auctionRepository.findOne(hashService.decode(id));

        if(auction != null) {
            return createDtoFromEntity(auction);
        }

        return null;
    }

    private AuctionDto createDtoFromEntity(Auction auction) {
        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setTitle(auction.getTitle());
        auctionDto.setEditorContent(auction.getEditorContent());
        auctionDto.setIsBuyout(auction.getBuyout());
        auctionDto.setIsBid(auction.getBid());
        auctionDto.setPrice(auction.getPrice());
        auctionDto.setAmount(auction.getAmount());
        auctionDto.setIsNew(auction.getNew());
        auctionDto.setCategory(auction.getCategory());

        return auctionDto;
    }
}
