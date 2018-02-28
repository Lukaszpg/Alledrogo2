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
    private User receiver;
    private Transaction transaction;
    private BigDecimal cash;
    private PaycheckType type;
    private String paypalTransactionId;
    private String token;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payer_id")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
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
