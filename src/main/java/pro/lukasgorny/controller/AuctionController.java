package pro.lukasgorny.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pro.lukasgorny.dto.AuctionDto;
import pro.lukasgorny.enums.TemplatesEnum;

import javax.validation.Valid;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
@RequestMapping("/auction")
public class AuctionController {

    @GetMapping("/sell")
    public ModelAndView sell() {
        ModelAndView modelAndView = new ModelAndView("auction/sell");
        modelAndView.addObject("auctionDto", new AuctionDto());
        return modelAndView;
    }

    @PostMapping("/sell")
    public ModelAndView createAuction(@Valid AuctionDto auctionDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        System.out.println("Bid: " + auctionDto.getIsBid());
        System.out.println("Buyout: " + auctionDto.getIsBuyout());

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(TemplatesEnum.SELL.getTemplateName());
        }

        return modelAndView;
    }
}
