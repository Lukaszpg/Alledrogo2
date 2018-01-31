package pro.lukasgorny.controller.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.lukasgorny.dto.auction.AuctionResultDto;
import pro.lukasgorny.dto.auction.SearchDto;
import pro.lukasgorny.service.auction.GetAuctionService;
import pro.lukasgorny.util.Urls;

import java.util.List;

/**
 * Created by Łukasz on 31.01.2018.
 */
@RestController
public class SearchRestController {

    private final GetAuctionService getAuctionService;

    @Autowired
    public SearchRestController(GetAuctionService getAuctionService) {
        this.getAuctionService = getAuctionService;
    }

    @GetMapping(Urls.Search.SEARCH_REST)
    public ResponseEntity<List<AuctionResultDto>> search(@RequestParam(required = false) String categoryId, @RequestParam(required = false) String searchString) {
        SearchDto searchDto = new SearchDto();
        searchDto.setCategoryId(categoryId);
        searchDto.setSearchString(searchString);

        List<AuctionResultDto> list = getAuctionService.getByCategoryIdAndTitle(searchDto);

        if (list != null && !list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
