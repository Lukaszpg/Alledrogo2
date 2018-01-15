package pro.lukasgorny.dto.auction;

import javax.validation.constraints.NotNull;

/**
 * Created by Łukasz on 23.11.2017.
 */
public class BuyoutSaveDto {

    @NotNull
    private Integer amountToBuy;
    private String auctionId;
    private String username;

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

    public Integer getAmountToBuy() {
        return amountToBuy;
    }

    public void setAmountToBuy(Integer amountToBuy) {
        this.amountToBuy = amountToBuy;
    }
}
