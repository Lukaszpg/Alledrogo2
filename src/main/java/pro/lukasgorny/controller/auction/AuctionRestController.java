package pro.lukasgorny.controller.auction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.lukasgorny.dto.auction.ObserveDto;
import pro.lukasgorny.service.auction.AuctionService;
import pro.lukasgorny.util.Urls;

import java.security.Principal;

/**
 * Created by Łukasz on 24.11.2017.
 */
@RestController
@RequestMapping(Urls.AuctionRest.MAIN)
public class AuctionRestController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionRestController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping(Urls.AuctionRest.OBSERVE)
    public Boolean observe(@PathVariable String id, Principal principal) {
        ObserveDto observeDto = new ObserveDto();
        observeDto.setUsername(principal.getName());
        observeDto.setAuctionId(id);
        auctionService.setObserveDto(observeDto);
        return auctionService.observe();
    }

    @GetMapping(Urls.AuctionRest.UNOBSERVE)
    public Boolean unobserve(@PathVariable String id, Principal principal) {
        ObserveDto observeDto = new ObserveDto();
        observeDto.setUsername(principal.getName());
        observeDto.setAuctionId(id);
        auctionService.setObserveDto(observeDto);
        return auctionService.unobserve();
    }

    @GetMapping(Urls.AuctionRest.IS_OBSERVING)
    public Boolean isObserving(@PathVariable String id, Principal principal) {
        ObserveDto observeDto = new ObserveDto();
        observeDto.setUsername(principal.getName());
        observeDto.setAuctionId(id);
        auctionService.setObserveDto(observeDto);
        return auctionService.isObserving();
    }
}
