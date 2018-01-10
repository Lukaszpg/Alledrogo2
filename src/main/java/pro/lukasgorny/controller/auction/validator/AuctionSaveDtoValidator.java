package pro.lukasgorny.controller.auction.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pro.lukasgorny.dto.auction.AuctionSaveDto;

public class AuctionSaveDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AuctionSaveDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AuctionSaveDto auctionSaveDto = (AuctionSaveDto) o;
        if (auctionSaveDto.getIsBid() == null && auctionSaveDto.getIsBuyout() == null) {
            errors.rejectValue("isBuyout", "error.pick.auction.type");
        }

        if (auctionSaveDto.getIsBid() != null && auctionSaveDto.getBidStartingPrice() == null) {
            errors.rejectValue("bidStartingPrice", "error.bid.starting.price");
        }

        if (auctionSaveDto.getIsBuyout() != null && auctionSaveDto.getPrice() == null) {
            errors.rejectValue("price", "error.buyout.price");
        }
    }
}
