package pro.lukasgorny.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pro.lukasgorny.dto.AuctionDto;

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
}
