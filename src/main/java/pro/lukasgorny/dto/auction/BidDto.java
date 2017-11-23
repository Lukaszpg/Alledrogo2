package pro.lukasgorny.dto.auction;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Łukasz on 23.11.2017.
 */
public class BidDto {

    @NotNull
    private BigDecimal amount;
    private String auctionId;
    private String username;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
}
