package pro.lukasgorny.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pro.lukasgorny.model.User;
import pro.lukasgorny.service.auction.GetAuctionService;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.util.Urls;

import java.security.Principal;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
@RequestMapping(Urls.User.MAIN)
public class UserController {

    private final UserService userService;
    private final GetAuctionService getAuctionService;

    @Autowired
    public UserController(UserService userService, GetAuctionService getAuctionService) {
        this.userService = userService;
        this.getAuctionService = getAuctionService;
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
}
