package pro.lukasgorny.controller.auction.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import pro.lukasgorny.dto.auction.AuctionSaveDto;

@Component
public class AuctionSaveDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return AuctionSaveDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AuctionSaveDto auctionSaveDto = (AuctionSaveDto) o;

        if(auctionSaveDto.getPhotos().length == 0 || auctionSaveDto.getPhotos()[0].getOriginalFilename().isEmpty()) {
            errors.rejectValue("photos", "error.no.photos");
        }

        if(auctionSaveDto.getPhotos().length > 3) {
            errors.rejectValue("photos", "error.too.many.photos");
        }

        for (MultipartFile multipartFile : auctionSaveDto.getPhotos()) {
            if(multipartFile.getSize() > 2097152) {
                errors.rejectValue("photos", "error.file.size.exceeded");
                break;
            }
        }

        if (auctionSaveDto.getIsBid() == null && auctionSaveDto.getIsBuyout() == null) {
            errors.rejectValue("isBuyout", "error.pick.auction.type");
        }

        if (auctionSaveDto.getIsBid() != null && auctionSaveDto.getBidStartingPrice() == null) {
            errors.rejectValue("bidStartingPrice", "error.bid.starting.price");
        }

        if (auctionSaveDto.getIsBuyout() != null && auctionSaveDto.getPrice() == null) {
            errors.rejectValue("price", "error.buyout.price");
        }

        if(auctionSaveDto.getAuctionDuration() == null && auctionSaveDto.getUntilOutOfItems() == null) {
            errors.rejectValue("auctionDuration", "NotNull.auctionSaveDto.auctionDuration");
        }

        if(auctionSaveDto.getAmount() == null || auctionSaveDto.getAmount() == 0 || auctionSaveDto.getAmount() < 0 ) {
            errors.rejectValue("amount", "NotNull.auctionSaveDto.amount");
        }
    }
}
