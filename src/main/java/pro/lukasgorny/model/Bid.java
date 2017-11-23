package pro.lukasgorny.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Created by Łukasz on 22.11.2017.
 */
@Entity
@Table(name = "bids")
public class Bid extends Model {

    private BigDecimal amount;
    private Boolean isWinning;
    private Auction auction;
    private User user;

    @ManyToOne
    @JoinColumn(name="auction_id")
    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public Boolean getWinning() {
        return isWinning;
    }

    public void setWinning(Boolean winning) {
        isWinning = winning;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
