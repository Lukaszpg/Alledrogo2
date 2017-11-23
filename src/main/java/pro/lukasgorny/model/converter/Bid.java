package pro.lukasgorny.model.converter;

import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.Model;
import pro.lukasgorny.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Łukasz on 22.11.2017.
 */
@Entity
@Table(name = "bids")
public class Bid extends Model {

    private Double amount;
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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
