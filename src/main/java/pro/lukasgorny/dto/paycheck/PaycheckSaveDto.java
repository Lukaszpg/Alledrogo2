package pro.lukasgorny.dto.paycheck;

import pro.lukasgorny.enums.PaycheckType;
import pro.lukasgorny.model.Auction;
import pro.lukasgorny.model.Transaction;
import pro.lukasgorny.model.User;

import java.math.BigDecimal;

/**
 * Created by Łukasz on 28.02.2018.
 */
public class PaycheckSaveDto {
    private Transaction transaction;
    private User payer;
    private User receiver;
    private BigDecimal cash;
    private PaycheckType paycheckType;
    private String paypalPaymentId;
    private String token;

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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
