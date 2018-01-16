package pro.lukasgorny.controller.search;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import pro.lukasgorny.dto.auction.SearchDto;
import pro.lukasgorny.util.Templates;

/**
 * Created by lukaszgo on 2018-01-16.
 */

@Controller
public class SearchController {

    @PostMapping("/search")
    public ModelAndView search(@Valid SearchDto searchDto) {
        ModelAndView modelAndView = new ModelAndView(Templates.SearchTemplates.SEARCH_RESULTS);
        return modelAndView;
    }

}
