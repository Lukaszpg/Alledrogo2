package pro.lukasgorny.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pro.lukasgorny.dto.AuctionDto;
import pro.lukasgorny.enums.TemplatesEnum;
import pro.lukasgorny.service.auction.CreateAuctionService;

import javax.validation.Valid;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
@RequestMapping("/auction")
public class AuctionController {

    private final CreateAuctionService createAuctionService;

    @Autowired
    public AuctionController(CreateAuctionService createAuctionService) {
        this.createAuctionService = createAuctionService;
    }

    @GetMapping("/sell")
    public ModelAndView sell() {
        ModelAndView modelAndView = new ModelAndView("auction/sell");
        modelAndView.addObject("auctionDto", new AuctionDto());
        return modelAndView;
    }

    @PostMapping("/sell")
    public ModelAndView createAuction(@Valid AuctionDto auctionDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(TemplatesEnum.SELL.getTemplateName());
        } else {
            createAuctionService.create(auctionDto);
            //TODO przekierowanie na stronę z sukcesem
        }

        return modelAndView;
    }
}
