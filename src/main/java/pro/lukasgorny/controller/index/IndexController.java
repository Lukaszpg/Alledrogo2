package pro.lukasgorny.controller.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.service.auction.GetAuctionService;
import pro.lukasgorny.util.Templates;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Controller
public class IndexController {

    private final GetAuctionService getAuctionService;

    @Autowired
    public IndexController(GetAuctionService getAuctionService) {
        this.getAuctionService = getAuctionService;
    }

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView(Templates.INDEX);
        modelAndView.addObject("auctions", getAuctionService.getTopAuctions());
        return modelAndView;
    }
}
