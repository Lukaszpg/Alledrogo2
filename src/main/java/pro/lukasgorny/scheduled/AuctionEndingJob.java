package pro.lukasgorny.scheduled;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.service.auction.AuctionService;
import pro.lukasgorny.service.auction.GetAuctionService;

/**
 * Created by lukaszgo on 2018-01-10.
 */
@Component
public class AuctionEndingJob {

    private final GetAuctionService getAuctionService;
    private final AuctionService auctionService;

    @Autowired
    public AuctionEndingJob(GetAuctionService getAuctionService, AuctionService auctionService) {
        this.getAuctionService = getAuctionService;
        this.auctionService = auctionService;
    }

    @Scheduled(fixedDelay = 1000)
    private void endAuctions() {
        List<Auction> auctionsToEnd = getAuctionService.getAllAuctionsToEnd();
        auctionsToEnd.forEach(auctionService::endAuction);
    }

}
