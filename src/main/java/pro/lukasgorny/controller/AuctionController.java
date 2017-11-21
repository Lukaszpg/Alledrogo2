package pro.lukasgorny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pro.lukasgorny.dto.AuctionDto;
import pro.lukasgorny.enums.Templates;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.service.auction.AuctionService;
import pro.lukasgorny.service.auction.CreateAuctionService;
import pro.lukasgorny.service.hash.HashService;

import javax.validation.Valid;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
@RequestMapping("/auction")
public class AuctionController {

    private final AuctionService auctionService;
    private final CreateAuctionService createAuctionService;
    private final HashService hashService;

    @Autowired
    public AuctionController(AuctionService auctionService, CreateAuctionService createAuctionService, HashService hashService) {
        this.auctionService = auctionService;
        this.createAuctionService = createAuctionService;
        this.hashService = hashService;
    }

    @GetMapping("/sell")
    public ModelAndView sell() {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.SELL);
        modelAndView.addObject("auctionDto", new AuctionDto());
        return modelAndView;
    }

    @PostMapping("/sell")
    public ModelAndView createAuction(@Valid AuctionDto auctionDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if(auctionDto.getIsBid() == null && auctionDto.getIsBuyout() == null) {
            bindingResult.rejectValue("isBuyout", "error.pick.auction.type");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.AuctionTemplates.SELL);
        } else {
            Auction auction = createAuctionService.create(auctionDto);
            auctionDto.setId(hashService.encode(auction.getId()));
            modelAndView.addObject("auctionDto", auctionDto);
            modelAndView.setViewName(Templates.AuctionTemplates.CREATE_SUCCESS);
        }

        return modelAndView;
    }

    @GetMapping("/item/{id}")
    public ModelAndView getAuction(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.ITEM);
        AuctionDto auctionDto = auctionService.getOne(id);
        modelAndView.addObject("auctionDto", auctionDto);

        return modelAndView;
    }
}
