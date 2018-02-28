package pro.lukasgorny.dto.auction;

import pro.lukasgorny.dto.user.UserExtendedDto;
import pro.lukasgorny.dto.user.UserResultDto;
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
    private String auctionTitle;
    private UserResultDto sellerDto;
    private UserExtendedDto buyerDetails;
    private String auctionId;
    private boolean paymentCompleted;

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

    public String getAuctionTitle() {
        return auctionTitle;
    }

    public void setAuctionTitle(String auctionTitle) {
        this.auctionTitle = auctionTitle;
    }

    public UserResultDto getSellerDto() {
        return sellerDto;
    }

    public void setSellerDto(UserResultDto sellerDto) {
        this.sellerDto = sellerDto;
    }

    public UserExtendedDto getBuyerDetails() {
        return buyerDetails;
    }

    public void setBuyerDetails(UserExtendedDto buyerDetails) {
        this.buyerDetails = buyerDetails;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public boolean isPaymentCompleted() {
        return paymentCompleted;
    }

    public void setPaymentCompleted(boolean paymentCompleted) {
        this.paymentCompleted = paymentCompleted;
    }
}
