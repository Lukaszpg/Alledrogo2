package pro.lukasgorny.controller.search;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.dto.auction.SearchDto;
import pro.lukasgorny.service.auction.GetAuctionService;
import pro.lukasgorny.util.Templates;

/**
 * Created by lukaszgo on 2018-01-16.
 */

@Controller
public class SearchController {

    private final GetAuctionService getAuctionService;

    @Autowired
    public SearchController(GetAuctionService getAuctionService) {
        this.getAuctionService = getAuctionService;
    }

    @PostMapping("/search")
    public ModelAndView search(@Valid SearchDto searchDto) {
        ModelAndView modelAndView = new ModelAndView(Templates.SearchTemplates.SEARCH_RESULTS);
        modelAndView.addObject("auctions", getAuctionService.getByCategoryIdAndTitle(searchDto));
        return modelAndView;
    }

}
