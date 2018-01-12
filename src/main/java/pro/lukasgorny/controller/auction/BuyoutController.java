package pro.lukasgorny.controller.auction;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.dto.auction.BuyoutDto;
import pro.lukasgorny.util.Urls;

/**
 * Created by lukaszgo on 2018-01-11.
 */
@Controller
@RequestMapping("/buyout")
public class BuyoutController {

    @PostMapping(Urls.Auction.CONFIRM_BUYOUT)
    public ModelAndView confirmBuyout(@Valid @ModelAttribute("buyoutDto") BuyoutDto buyoutDto) {
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }
}
