package pro.lukasgorny.service.auction;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.AuctionDto;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.repository.AuctionRepository;
import pro.lukasgorny.service.category.CategoryService;

/**
 * Created by Łukasz on 20.11.2017.
 */
@Service
public class CreateAuctionServiceImpl implements CreateAuctionService {

    private final ModelMapper modelMapper;
    private final AuctionRepository auctionRepository;
    private final CategoryService categoryService;

    @Autowired
    public CreateAuctionServiceImpl(ModelMapper modelMapper, AuctionRepository auctionRepository, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.auctionRepository = auctionRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void create(AuctionDto auctionDto) {
        Auction auction = createEntityFromDto(auctionDto);
        auctionRepository.save(auction);
    }

    private Auction createEntityFromDto(AuctionDto auctionDto) {
        Auction auction = modelMapper.map(auctionDto, Auction.class);
        auction.setCategory(categoryService.getById(auctionDto.getCategoryId()));
        return auction;
    }
}
