package pro.lukasgorny.controller.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.controller.auction.validator.AuctionSaveDtoValidator;
import pro.lukasgorny.controller.auction.validator.BidDtoValidator;
import pro.lukasgorny.controller.auction.validator.BuyoutDtoValidator;
import pro.lukasgorny.dto.auction.AuctionSaveDto;
import pro.lukasgorny.dto.auction.BidSaveDto;
import pro.lukasgorny.dto.auction.BuyoutSaveDto;
import pro.lukasgorny.service.auction.AuctionService;
import pro.lukasgorny.service.auction.CreateTransactionService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.service.auction.GetAuctionService;
import pro.lukasgorny.service.auction.CreateAuctionService;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.util.Urls;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
@RequestMapping(Urls.Auction.MAIN)
public class AuctionController {

    private final UserService userService;
    private final GetAuctionService getAuctionService;
    private final CreateAuctionService createAuctionService;
    private final HashService hashService;
    private final CreateTransactionService createTransactionService;
    private final AuctionService auctionService;
    private final AuctionSaveDtoValidator auctionSaveDtoValidator;
    private final BidDtoValidator bidDtoValidator;
    private final BuyoutDtoValidator buyoutDtoValidator;

    @Autowired
    public AuctionController(UserService userService, GetAuctionService getAuctionService, CreateAuctionService createAuctionService,
            HashService hashService, CreateTransactionService createTransactionService, AuctionService auctionService,
            AuctionSaveDtoValidator auctionSaveDtoValidator, BidDtoValidator bidDtoValidator, BuyoutDtoValidator buyoutDtoValidator) {
        this.userService = userService;
        this.getAuctionService = getAuctionService;
        this.createAuctionService = createAuctionService;
        this.hashService = hashService;
        this.createTransactionService = createTransactionService;
        this.auctionService = auctionService;
        this.auctionSaveDtoValidator = auctionSaveDtoValidator;
        this.bidDtoValidator = bidDtoValidator;
        this.buyoutDtoValidator = buyoutDtoValidator;
    }

    @GetMapping(Urls.Auction.SELL)
    public ModelAndView sell() {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.SELL);
        AuctionSaveDto auctionSaveDto = new AuctionSaveDto();
        auctionSaveDto.setIsBid(false);
        auctionSaveDto.setIsBuyout(true);
        modelAndView.addObject("auctionSaveDto", auctionSaveDto);
        return modelAndView;
    }

    @PostMapping(Urls.Auction.SELL)
    public ModelAndView createAuction(@Valid AuctionSaveDto auctionSaveDto, BindingResult bindingResult, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        auctionSaveDtoValidator.validate(auctionSaveDto, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.AuctionTemplates.SELL);
        } else {
            auctionSaveDto.setSeller(userService.getByEmail(principal.getName()));

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

    @GetMapping(Urls.Auction.GET)
    public ModelAndView getAuction(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.ITEM);
        modelAndView.addObject("auctionDto", getAuctionService.getOne(id));
        modelAndView.addObject("bidDto", new BidSaveDto());
        modelAndView.addObject("buyoutDto", new BuyoutSaveDto());
        return modelAndView;
    }

    @PostMapping(Urls.Auction.BID)
    public ModelAndView bid(@PathVariable String id, @ModelAttribute("bidDto") @Valid BidSaveDto bidSaveDto, BindingResult bindingResult,
            Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        bidSaveDto.setUsername(principal.getName());
        bidSaveDto.setAuctionId(id);

        if (!getAuctionService.auctionExists(id)) {
            modelAndView.setViewName(Templates.AuctionTemplates.ITEM);
            return modelAndView;
        }

        if (auctionService.checkHasAuctionEnded(id)) {
            modelAndView.setViewName(Urls.Auction.AUCTION_ENDED_REDIRECT);
            return modelAndView;
        }

        bidDtoValidator.validate(bidSaveDto, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("auctionDto", getAuctionService.getOne(id));
            modelAndView.addObject("buyoutDto", new BuyoutSaveDto());
            modelAndView.setViewName(Templates.AuctionTemplates.ITEM);
            return modelAndView;
        }

        bidSaveDto.setWinning(true);
        createTransactionService.createTransaction(bidSaveDto);
        modelAndView.setViewName(String.format(Urls.Auction.BID_SUCCESS_REDIRECT, id));

        return modelAndView;
    }

    @GetMapping(Urls.Auction.BID_SUCCESS)
    public ModelAndView bidSuccess(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.BID_SUCCESS);
        modelAndView.addObject("auctionId", id);
        return modelAndView;
    }

    @GetMapping(Urls.Auction.AUCTION_ENDED)
    public ModelAndView auctionEnded() {
        return new ModelAndView(Templates.AuctionTemplates.AUCTION_ENDED);
    }

    @PostMapping(Urls.Auction.CONFIRM_BUYOUT)
    public ModelAndView confirmBuyout(@PathVariable("id") String id, @Valid @ModelAttribute("buyoutDto") BuyoutSaveDto buyoutSaveDto,
            BindingResult bindingResult, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        if (!getAuctionService.auctionExists(id)) {
            modelAndView.setViewName(Templates.AuctionTemplates.ITEM);
            return modelAndView;
        }

        if (auctionService.checkHasAuctionEnded(id)) {
            modelAndView.setViewName(Urls.Auction.AUCTION_ENDED_REDIRECT);
            return modelAndView;
        }

        buyoutSaveDto.setUsername(principal.getName());
        buyoutSaveDto.setAuctionId(id);
        buyoutDtoValidator.validate(buyoutSaveDto, bindingResult);

        modelAndView.addObject("buyoutDto", buyoutSaveDto);
        modelAndView.addObject("auctionDto", getAuctionService.getOne(id));

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("bidDto", new BidSaveDto());
            modelAndView.setViewName(Templates.AuctionTemplates.ITEM);
            return modelAndView;
        }

        modelAndView.setViewName(Templates.AuctionTemplates.CONFIRM_BUYOUT);

        return modelAndView;
    }

    @PostMapping(Urls.Auction.BUYOUT)
    public ModelAndView buyout(@PathVariable("id") String id, @Valid BuyoutSaveDto buyoutSaveDto, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        buyoutSaveDto.setAuctionId(id);
        buyoutSaveDto.setUsername(principal.getName());
        createTransactionService.createTransaction(buyoutSaveDto);
        modelAndView.setViewName(String.format(Urls.Auction.BUYOUT_SUCCESS_REDIRECT, id));
        return modelAndView;
    }

    @GetMapping(Urls.Auction.BUYOUT_SUCCESS)
    public ModelAndView buyoutSuccess(@PathVariable("id") String id) {
        ModelAndView modelAndView = new ModelAndView(Templates.AuctionTemplates.BUYOUT_SUCCESS);
        modelAndView.addObject("auctionId", id);
        return modelAndView;
    }
}
