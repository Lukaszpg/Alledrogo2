package pro.lukasgorny.model;

import pro.lukasgorny.enums.TransactionType;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Łukasz on 12.01.2018.
 */
@Entity
@Table(name = "transactions")
public class Transaction extends Model {

    private BigDecimal offer;
    private Boolean isWinning;
    protected Auction auction;
    protected User user;
    private Integer amountBought;
    private TransactionType transactionType;
    private Rating buyerRating;
    private Rating sellerRating;

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

    public BigDecimal getOffer() {
        return offer;
    }

    public void setOffer(BigDecimal offer) {
        this.offer = offer;
    }

    public Boolean getIsWinning() {
        return isWinning;
    }

    public void setIsWinning(Boolean winning) {
        isWinning = winning;
    }

    public Integer getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(Integer amountBought) {
        this.amountBought = amountBought;
    }

    @Enumerated(EnumType.STRING)
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @ManyToOne
    @JoinColumn(name="buyer_rating_id")
    public Rating getBuyerRating() {
        return buyerRating;
    }

    public void setBuyerRating(Rating buyerRating) {
        this.buyerRating = buyerRating;
    }

    @ManyToOne
    @JoinColumn(name="seller_rating_id")
    public Rating getSellerRating() {
        return sellerRating;
    }

    public void setSellerRating(Rating sellerRating) {
        this.sellerRating = sellerRating;
    }
}
