package pro.lukasgorny.controller.auction.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pro.lukasgorny.dto.auction.BidDto;
import pro.lukasgorny.service.auction.AuctionService;
import pro.lukasgorny.service.auction.CreateBidService;

public class BidDtoValidator implements Validator {

    private final AuctionService auctionService;
    private final CreateBidService createBidService;

    public BidDtoValidator(AuctionService auctionService, CreateBidService createBidService) {
        this.auctionService = auctionService;
        this.createBidService = createBidService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BidDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BidDto bidDto = (BidDto) o;
        if(auctionService.checkIsBiddingUserAuctionCreator(bidDto.getAuctionId(), bidDto.getUsername())) {
            errors.rejectValue("amount","error.bid.same.user");
        } else {
            if(bidDto.getAmount() == null) {
                errors.rejectValue("amount", "NotNull.bidDto.amount");
            } else if(createBidService.checkOfferLowerThanCurrentPrice()) {
                errors.rejectValue("amount", "error.bid.price.lower");
            }
        }
    }
}
