package pro.lukasgorny.controller.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pro.lukasgorny.dto.auction.BidSaveDto;
import pro.lukasgorny.service.auction.AuctionService;
import pro.lukasgorny.service.auction.CreateTransactionService;

@Component
public class BidDtoValidator implements Validator {

    private final AuctionService auctionService;
    private final CreateTransactionService createTransactionService;

    @Autowired
    public BidDtoValidator(AuctionService auctionService, CreateTransactionService createTransactionService) {
        this.auctionService = auctionService;
        this.createTransactionService = createTransactionService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BidSaveDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BidSaveDto bidSaveDto = (BidSaveDto) o;
        if(auctionService.checkIsUserAuctionCreator(bidSaveDto.getAuctionId(), bidSaveDto.getUsername())) {
            errors.rejectValue("offeredPrice","error.bid.same.user");
        } else {
            if(bidSaveDto.getOfferedPrice() == null) {
                errors.rejectValue("offeredPrice", "NotNull.bidSaveDto.price");
            } else if(createTransactionService.checkOfferLowerThanCurrentPrice(bidSaveDto)) {
                errors.rejectValue("offeredPrice", "error.bid.price.lower");
            }
        }
    }
}
