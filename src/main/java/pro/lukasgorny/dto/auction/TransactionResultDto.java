package pro.lukasgorny.dto.auction;

import pro.lukasgorny.enums.TransactionType;

import java.math.BigDecimal;

/**
 * Created by Łukasz on 12.01.2018.
 */
public class TransactionResultDto {

    private String buyerName;
    private BigDecimal offeredPrice;
    private TransactionType transactionType;

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public BigDecimal getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(BigDecimal offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
