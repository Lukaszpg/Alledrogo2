package pro.lukasgorny.service.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pro.lukasgorny.dto.auction.AuctionSaveDto;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.Photo;
import pro.lukasgorny.repository.AuctionRepository;
import pro.lukasgorny.service.category.GetCategoryService;
import pro.lukasgorny.service.storage.StorageService;

import java.time.LocalDateTime;

/**
 * Created by Łukasz on 20.11.2017.
 */
@Service
public class CreateAuctionServiceImpl implements CreateAuctionService {

    private final AuctionRepository auctionRepository;
    private final GetCategoryService getCategoryService;
    private final StorageService storageService;
    private final PhotoService photoService;

    @Autowired
    public CreateAuctionServiceImpl(AuctionRepository auctionRepository, GetCategoryService getCategoryService, StorageService storageService,
            PhotoService photoService) {
        this.auctionRepository = auctionRepository;
        this.getCategoryService = getCategoryService;
        this.storageService = storageService;
        this.photoService = photoService;
    }

    @Override
    @Transactional
    public Auction create(AuctionSaveDto auctionSaveDto) {
        Auction auction = createEntityFromDto(auctionSaveDto);
        auction = auctionRepository.save(auction);
        savePhotos(auctionSaveDto, auction);
        return auction;
    }

    private Auction createEntityFromDto(AuctionSaveDto auctionSaveDto) {
        Auction auction = new Auction();
        auction.setCategory(getCategoryService.getById(auctionSaveDto.getCategoryId()));
        auction.setTitle(auctionSaveDto.getTitle());
        auction.setNew(auctionSaveDto.getIsNew());
        auction.setEditorContent(auctionSaveDto.getEditorContent());
        auction.setBuyout(calculateBooleanFieldValue(auctionSaveDto.getIsBuyout()));
        auction.setBid(calculateBooleanFieldValue(auctionSaveDto.getIsBid()));
        auction.setPrice(auctionSaveDto.getPrice());
        auction.setAmount(auctionSaveDto.getAmount());
        auction.setCurrentAmount(auctionSaveDto.getAmount());
        auction.setBidMinimalPrice(auctionSaveDto.getBidMinimalPrice());
        auction.setBidStartingPrice(auctionSaveDto.getBidStartingPrice());
        auction.setEndDate(calculateEndDateIfNotUntilOutOfItems(auctionSaveDto));
        auction.setSeller(auctionSaveDto.getSeller());
        auction.setDeleted(false);
        auction.setHasEnded(false);
        auction.setUntilOutOfItems(calculateBooleanFieldValue(auctionSaveDto.getUntilOutOfItems()));
        return auction;
    }

    private void savePhotos(AuctionSaveDto auctionSaveDto, Auction auction) {
        for(int i = 0; i < auctionSaveDto.getPhotos().length; i++) {
            MultipartFile file = auctionSaveDto.getPhotos()[i];
            String path = storageService.store(file, auction);
            Photo photo = new Photo();
            photo.setAuction(auction);
            photo.setPhotoOrder(i);
            photo.setStoragePath(path);
            photo.setIsMain(i == 0);
            photoService.save(photo);
        }
    }

    private LocalDateTime calculateEndDateIfNotUntilOutOfItems(AuctionSaveDto auctionSaveDto) {
        if(auctionSaveDto.getUntilOutOfItems() == null) {
            return LocalDateTime.now().plusDays(auctionSaveDto.getAuctionDuration());
        }

        return null;
    }

    private Boolean calculateBooleanFieldValue(Boolean field) {
        return field != null;
    }
}
