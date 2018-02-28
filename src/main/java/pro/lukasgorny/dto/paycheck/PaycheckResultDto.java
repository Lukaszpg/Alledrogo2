package pro.lukasgorny.dto.paycheck;

import java.math.BigDecimal;

/**
 * Created by lukaszgo on 2018-02-28.
 */
public class PaycheckResultDto {
    private String auctionTitle;
    private String payerName;
    private String receiverName;
    private BigDecimal amount;
    private String paycheckType;
    private String auctionId;

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaycheckType() {
        return paycheckType;
    }

    public void setPaycheckType(String paycheckType) {
        this.paycheckType = paycheckType;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }
}
