package pro.lukasgorny.dto.auction;

import java.math.BigDecimal;

/**
 * Created by Łukasz on 23.11.2017.
 */
public class BidSaveDto {

    private BigDecimal offeredPrice;
    private String auctionId;
    private String username;
    private Boolean isWinning;

    public BigDecimal getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(BigDecimal offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getWinning() {
        return isWinning;
    }

    public void setWinning(Boolean winning) {
        isWinning = winning;
    }
}
