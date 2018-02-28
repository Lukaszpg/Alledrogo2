package pro.lukasgorny.dto.paycheck;

import pro.lukasgorny.enums.PaycheckType;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.User;

import java.math.BigDecimal;

/**
 * Created by Łukasz on 28.02.2018.
 */
public class PaycheckSaveDto {
    private Auction auction;
    private User payer;
    private BigDecimal cash;
    private PaycheckType paycheckType;
    private String paypalPaymentId;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getPayer() {
        return payer;
    }

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public PaycheckType getPaycheckType() {
        return paycheckType;
    }

    public void setPaycheckType(PaycheckType paycheckType) {
        this.paycheckType = paycheckType;
    }

    public String getPaypalPaymentId() {
        return paypalPaymentId;
    }

    public void setPaypalPaymentId(String paypalPaymentId) {
        this.paypalPaymentId = paypalPaymentId;
    }
}
