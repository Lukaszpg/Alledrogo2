package pro.lukasgorny.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.dto.Rating.RatingSaveDto;
import pro.lukasgorny.model.User;
import pro.lukasgorny.service.auction.GetAuctionService;
import pro.lukasgorny.service.auction.GetTransactionService;
import pro.lukasgorny.service.rating.CreateRatingService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.util.Urls;

import java.security.Principal;
import javax.validation.Valid;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
@RequestMapping(Urls.User.MAIN)
public class UserController {

    private final UserService userService;
    private final GetAuctionService getAuctionService;
    private final GetTransactionService getTransactionService;
    private final CreateRatingService createRatingService;

    @Autowired
    public UserController(UserService userService, GetAuctionService getAuctionService, GetTransactionService getTransactionService,
            CreateRatingService createRatingService) {
        this.userService = userService;
        this.getAuctionService = getAuctionService;
        this.getTransactionService = getTransactionService;
        this.createRatingService = createRatingService;
    }

    @GetMapping(Urls.User.ACCOUNT)
    public ModelAndView account(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.ACCOUNT);
        return modelAndView;
    }

    @GetMapping(Urls.User.OBSERVING)
    public ModelAndView observing(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.OBSERVING);
        User user = userService.getByEmail(principal.getName());
        modelAndView.addObject("observedAuctions", getAuctionService.getAllObservedByUserId(user.getId()));
        return modelAndView;
    }

    @GetMapping(Urls.User.RATING_BOUGHT)
    public ModelAndView listRatingAllBought(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.POST_RATING_BOUGHT);
        modelAndView.addObject("transactions", getTransactionService.getAllBoughtItemsWithoutRatingForBuyerByUserEmail(principal.getName()));
        return modelAndView;
    }

    @GetMapping(Urls.User.RATING_SOLD)
    public ModelAndView listRatingAllSold(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.POST_RATING_SOLD);
        modelAndView.addObject("transactions", getTransactionService.getAllBoughtItemsWithoutRatingForBuyerByUserEmail(principal.getName()));
        return modelAndView;
    }

    @GetMapping(Urls.User.CREATE_RATING)
    public ModelAndView getCreateRatingForm(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.CREATE_RATING);
        RatingSaveDto ratingSaveDto = new RatingSaveDto();
        ratingSaveDto.setTransactionId(id);
        modelAndView.addObject("ratingDto", ratingSaveDto);
        return modelAndView;
    }

    @PostMapping(Urls.User.CREATE_RATING)
    public ModelAndView createRating(@Valid RatingSaveDto ratingSaveDto, BindingResult bindingResult, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.UserTemplates.CREATE_RATING);
            return modelAndView;
        }

        createRatingService.createRating(ratingSaveDto);

        return modelAndView;
    }

    @GetMapping(Urls.User.ITEMS_SOLD)
    public ModelAndView getUserSoldItems(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.ITEMS_SOLD);
        modelAndView.addObject("transactions", getTransactionService.getAllSoldItemsByUserEmail(principal.getName()));
        return modelAndView;
    }

    @GetMapping(Urls.User.ITEMS_BOUGHT)
    public ModelAndView getUserBoughtItems(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.ITEMS_BOUGHT);
        modelAndView.addObject("transactions", getTransactionService.getAllBoughtItemsByUserEmail(principal.getName()));
        return modelAndView;
    }
}
