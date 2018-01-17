package pro.lukasgorny.dto.auction;

import pro.lukasgorny.enums.TransactionType;

import java.math.BigDecimal;

/**
 * Created by Łukasz on 12.01.2018.
 */
public class TransactionResultDto {

    private String id;
    private String buyerName;
    private BigDecimal price;
    private TransactionType transactionType;
    private Integer amountBought;
    private String created;
    private AuctionResultDto auctionResultDto;

    public TransactionResultDto() {
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Integer getAmountBought() {
        return amountBought;
    }

    public void setAmountBought(Integer amountBought) {
        this.amountBought = amountBought;
    }

    public AuctionResultDto getAuctionResultDto() {
        return auctionResultDto;
    }

    public void setAuctionResultDto(AuctionResultDto auctionResultDto) {
        this.auctionResultDto = auctionResultDto;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
