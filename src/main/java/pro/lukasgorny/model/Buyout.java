package pro.lukasgorny.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Łukasz on 15.12.2017.
 */
@Entity
@Table(name = "buyouts")
public class Buyout extends Model {
    private Integer amount;
    protected Auction auction;
    protected User user;

    @ManyToOne
    @JoinColumn(name="auction_id")
    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
