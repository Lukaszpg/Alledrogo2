package pro.lukasgorny.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pro.lukasgorny.controller.user.validator.UserExtendedDtoValidator;
import pro.lukasgorny.dto.ChangeEmailDto;
import pro.lukasgorny.dto.ChangePasswordDto;
import pro.lukasgorny.dto.Rating.RatingResultDto;
import pro.lukasgorny.dto.Rating.RatingSaveDto;
import pro.lukasgorny.dto.UserExtendedDto;
import pro.lukasgorny.enums.RatingTypeEnum;
import pro.lukasgorny.model.User;
import pro.lukasgorny.service.auction.GetAuctionService;
import pro.lukasgorny.service.auction.GetTransactionService;
import pro.lukasgorny.service.rating.CreateRatingService;
import pro.lukasgorny.service.rating.GetRatingService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.util.Urls;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

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
    private final GetRatingService getRatingService;
    private final UserExtendedDtoValidator userExtendedDtoValidator;

    @Autowired
    public UserController(UserService userService, GetAuctionService getAuctionService, GetTransactionService getTransactionService,
                          CreateRatingService createRatingService, GetRatingService getRatingService, UserExtendedDtoValidator userExtendedDtoValidator) {
        this.userService = userService;
        this.getAuctionService = getAuctionService;
        this.getTransactionService = getTransactionService;
        this.createRatingService = createRatingService;
        this.getRatingService = getRatingService;
        this.userExtendedDtoValidator = userExtendedDtoValidator;
    }

    @GetMapping(Urls.User.OBSERVING)
    public ModelAndView observing(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.OBSERVING);
        User user = userService.getByEmail(principal.getName());
        modelAndView.addObject("observedAuctions", getAuctionService.getAllObservedByUserId(user.getId()));
        return modelAndView;
    }

    @GetMapping(Urls.User.RATING)
    public ModelAndView listRatingAll(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.POST_RATING);
        modelAndView.addObject("transactionsSold", getTransactionService.getAllSoldItemsWithoutRatingForSellerByUserEmail(principal.getName()));
        modelAndView.addObject("transactionsBought", getTransactionService.getAllBoughtItemsWithoutRatingForBuyerByUserEmail(principal.getName()));
        return modelAndView;
    }

    @GetMapping(Urls.User.CREATE_RATING)
    public ModelAndView getCreateRatingForm(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.CREATE_RATING);
        RatingSaveDto ratingSaveDto = new RatingSaveDto();
        ratingSaveDto.setTransactionId(id);
        modelAndView.addObject("ratingSaveDto", ratingSaveDto);
        return modelAndView;
    }

    @PostMapping(Urls.User.CREATE_RATING)
    public ModelAndView createRating(@PathVariable String id, @Valid RatingSaveDto ratingSaveDto, BindingResult bindingResult, Principal principal) {
        ModelAndView modelAndView = new ModelAndView();

        ratingSaveDto.setIssuerName(principal.getName());
        ratingSaveDto.setTransactionId(id);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.UserTemplates.CREATE_RATING);
            return modelAndView;
        }

        createRatingService.createRating(ratingSaveDto);
        modelAndView.setViewName(Urls.User.CREATE_RATING_SUCCESS_REDIRECT);

        return modelAndView;
    }

    @GetMapping(Urls.User.CREATE_RATING_SUCCESS)
    public ModelAndView createRatingSuccess() {
        return new ModelAndView(Templates.UserTemplates.CREATE_RATING_SUCCESS);
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

    @GetMapping(Urls.User.MY_RATINGS)
    public ModelAndView getUserRatings(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.MY_RATINGS);
        List<RatingResultDto> receivedRatings = getRatingService.getReceivedRatingsForUser(principal.getName());
        modelAndView.addObject("receivedRatings", receivedRatings);
        modelAndView.addObject("receivedRatingsCountPositive", getRatingService.getCommentCountByType(receivedRatings, RatingTypeEnum.POSITIVE));
        modelAndView.addObject("receivedRatingsCountNegative", getRatingService.getCommentCountByType(receivedRatings, RatingTypeEnum.NEGATIVE));
        modelAndView.addObject("receivedRatingsCountNeutral", getRatingService.getCommentCountByType(receivedRatings, RatingTypeEnum.NEUTRAL));
        modelAndView.addObject("issuedRatingsCountPositive", getRatingService.getCommentCountByType(receivedRatings, RatingTypeEnum.POSITIVE));
        modelAndView.addObject("issuedRatingsCountNegative", getRatingService.getCommentCountByType(receivedRatings, RatingTypeEnum.NEGATIVE));
        modelAndView.addObject("issuedRatingsCountNeutral", getRatingService.getCommentCountByType(receivedRatings, RatingTypeEnum.NEUTRAL));
        return modelAndView;
    }

    @GetMapping(Urls.User.ACCOUNT)
    public ModelAndView getMyAccount(Principal principal) {
        ModelAndView modelAndView = new ModelAndView(Templates.UserTemplates.ACCOUNT);
        modelAndView.addObject("userExtendedDto", userService.getUserData(principal.getName()));
        modelAndView.addObject("changePasswordDto", new ChangePasswordDto());
        modelAndView.addObject("changeEmailDto", new ChangeEmailDto());
        modelAndView.addObject("changePasswordTab", false);
        modelAndView.addObject("changeEmailTab", false);
        return modelAndView;
    }

    @PostMapping(Urls.User.ACCOUNT)
    public ModelAndView saveUserData(@ModelAttribute("userExtendedDto") UserExtendedDto userExtendedDto, Principal principal, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("changePasswordTab", false);
        modelAndView.addObject("changeEmailTab", false);

        userExtendedDtoValidator.validate(userExtendedDto, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.UserTemplates.ACCOUNT);
            modelAndView.addObject("changePasswordDto", new ChangePasswordDto());
            modelAndView.addObject("changeEmailDto", new ChangeEmailDto());
            return modelAndView;
        }

        userExtendedDto.setEmail(principal.getName());
        userService.setAndSaveUserData(userExtendedDto);
        modelAndView.setViewName(Templates.INDEX);

        return modelAndView;
    }

    @PostMapping(Urls.User.CHANGE_PASSWORD)
    public ModelAndView changeUserPassword(@Valid ChangePasswordDto changePasswordDto, BindingResult bindingResult, Principal principal, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userExtendedDto", userService.getUserData(principal.getName()));
        modelAndView.addObject("changeEmailDto", new ChangeEmailDto());

        changePasswordDto.setEmail(principal.getName());

        if(!userService.isUserPasswordMatchWithInput(changePasswordDto.getActualPassword(), changePasswordDto.getEmail())) {
            bindingResult.rejectValue("actualPassword", "error.password.actual.no.match");
        }

        if(userService.isNewPasswordSameAsOldPassword(changePasswordDto)) {
            bindingResult.rejectValue("newPassword", "error.password.same.as.old");
        }

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.UserTemplates.ACCOUNT);
            modelAndView.addObject("changePasswordTab", true);
            modelAndView.addObject("changeEmailTab", false);
            return modelAndView;
        }

        userService.changeUserPassword(changePasswordDto);
        modelAndView.setViewName(Urls.User.CHANGE_PASSWORD_SUCCESS_REDIRECT);
        httpSession.invalidate();

        return modelAndView;
    }

    @GetMapping(Urls.User.CHANGE_PASSWORD_SUCCESS)
    public ModelAndView changeUserPasswordSuccess() {
        return new ModelAndView(Templates.UserTemplates.CHANGE_PASSWORD_SUCCESS);
    }

    @PostMapping(Urls.User.CHANGE_EMAIL)
    public ModelAndView changeUserEmail(@Valid ChangeEmailDto changeEmailDto, BindingResult bindingResult, Principal principal, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userExtendedDto", userService.getUserData(principal.getName()));
        modelAndView.addObject("changePasswordDto", new ChangePasswordDto());
        changeEmailDto.setEmail(principal.getName());

        if(!userService.isUserPasswordMatchWithInput(changeEmailDto.getActualPassword(), principal.getName())) {
            bindingResult.rejectValue("actualPassword", "error.password.actual.no.match");
        }

        if(userService.getByEmail(changeEmailDto.getNewEmail()) != null) {
            bindingResult.rejectValue("newEmail", "error.user.exists");
        }

        if(bindingResult.hasErrors()) {
            modelAndView.setViewName(Templates.UserTemplates.ACCOUNT);
            modelAndView.addObject("changePasswordTab", false);
            modelAndView.addObject("changeEmailTab", true);
            return modelAndView;
        }

        userService.changeUserEmail(changeEmailDto);
        modelAndView.setViewName(Urls.User.CHANGE_EMAIL_SUCCESS_REDIRECT);
        httpSession.invalidate();

        return modelAndView;
    }

    @GetMapping(Urls.User.CHANGE_EMAIL_SUCCESS)
    public ModelAndView changeUserEmailSuccess() {
        return new ModelAndView(Templates.UserTemplates.CHANGE_EMAIL_SUCCESS);
    }
}
