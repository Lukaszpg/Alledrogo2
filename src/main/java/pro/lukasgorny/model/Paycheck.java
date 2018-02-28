package pro.lukasgorny.model;

import pro.lukasgorny.enums.PaycheckType;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Łukasz on 28.02.2018.
 */
@Entity
@Table(name = "paychecks")
public class Paycheck extends Model {
    private User payer;
    private Auction auction;
    private BigDecimal cash;
    private PaycheckType type;
    private String paypalTransactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    @Enumerated(EnumType.STRING)
    public PaycheckType getType() {
        return type;
    }

    public void setType(PaycheckType type) {
        this.type = type;
    }

    public String getPaypalTransactionId() {
        return paypalTransactionId;
    }

    public void setPaypalTransactionId(String paypalTransactionId) {
        this.paypalTransactionId = paypalTransactionId;
    }
}
