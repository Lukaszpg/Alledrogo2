package pro.lukasgorny.controller.auction.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pro.lukasgorny.dto.auction.BuyoutSaveDto;
import pro.lukasgorny.service.auction.AuctionService;
import pro.lukasgorny.service.auction.GetAuctionService;

@Component
public class BuyoutDtoValidator implements Validator {

    private final AuctionService auctionService;
    private final GetAuctionService getAuctionService;

    @Autowired
    public BuyoutDtoValidator(AuctionService auctionService, GetAuctionService getAuctionService) {
        this.auctionService = auctionService;
        this.getAuctionService = getAuctionService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BuyoutSaveDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BuyoutSaveDto buyoutSaveDto = (BuyoutSaveDto) o;
        if(auctionService.checkIsBiddingUserAuctionCreator(buyoutSaveDto.getAuctionId(), buyoutSaveDto.getUsername())) {
            errors.rejectValue("amountToBuy","error.buyout.same.user");
        } else {
            Integer currentItemsAmount = getAuctionService.getAuctionCurrentItemAmount(buyoutSaveDto.getAuctionId());

            if(buyoutSaveDto.getAmountToBuy() == null) {
                errors.rejectValue("amountToBuy", "NotNull.buyoutSaveDto.amountToBuy");
            } else if(buyoutSaveDto.getAmountToBuy() < 0) {
                errors.rejectValue("amountToBuy", "Min.buyoutSaveDto.amountToBuy");
            } else if(buyoutSaveDto.getAmountToBuy() > currentItemsAmount) {
                errors.rejectValue("amountToBuy", "error.buyout.too.much.items");
            }
        }
    }
}
