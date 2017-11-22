package pro.lukasgorny.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.AuctionSaveDto;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.service.auction.GetAuctionService;
import pro.lukasgorny.service.auction.CreateAuctionService;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.util.Urls;

import javax.validation.Valid;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
@RequestMapping(Urls.Auction.MAIN)
public class AuctionController {

    private final GetAuctionService getAuctionService;
    private final CreateAuctionService createAuctionService;
    private final HashService hashService;

    @Autowired
    public AuctionController(GetAuctionService getAuctionService, CreateAuctionService createAuctionService, HashService hashService) {
        this.getAuctionService = getAuctionService;
        this.createAuctionService = createAuctionService;
        this.hashService = hashService;
    }

    @GetMapping(Urls.Auction.SELL)
    public ModelAndView sell() {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.SELL);
        modelAndView.addObject("auctionSaveDto", new AuctionSaveDto());
        return modelAndView;
    }

    @PostMapping(Urls.Auction.SELL)
    public ModelAndView createAuction(@Valid AuctionSaveDto auctionSaveDto, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if(auctionSaveDto.getIsBid() == null && auctionSaveDto.getIsBuyout() == null) {
            bindingResult.rejectValue("isBuyout", "error.pick.auction.type");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.AuctionTemplates.SELL);
        } else {
            Auction auction = createAuctionService.create(auctionSaveDto);
            ModelMap modelMap = new ModelMap();
            modelMap.addAttribute("auctionId", hashService.encode(auction.getId()));
            return new ModelAndView(Urls.Auction.CREATE_SUCCESS_REDIRECT, modelMap);
        }

        return modelAndView;
    }

    @GetMapping(Urls.Auction.CREATE_SUCCESS)
    public ModelAndView createSuccess(@ModelAttribute("auctionId") String id) {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.CREATE_SUCCESS);
        modelAndView.addObject("auctionId", id);
        return modelAndView;
    }

    @GetMapping(Urls.Auction.ITEM)
    public ModelAndView getAuction(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.ITEM);
        AuctionResultDto auctionResultDto = getAuctionService.getOne(id);
        modelAndView.addObject("auctionDto", auctionResultDto);

        return modelAndView;
    }
}
