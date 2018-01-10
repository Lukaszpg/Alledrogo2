package pro.lukasgorny.controller.auction.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pro.lukasgorny.dto.auction.BuyoutDto;

public class BuyoutDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return BuyoutDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BuyoutDto buyoutDto = (BuyoutDto) o;
    }
}
