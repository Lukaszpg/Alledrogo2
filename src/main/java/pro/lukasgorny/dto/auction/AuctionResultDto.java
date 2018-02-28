package pro.lukasgorny.dto.auction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pro.lukasgorny.dto.user.UserResultDto;
import pro.lukasgorny.model.Category;

import java.math.BigDecimal;

/**
 * Created by Łukasz on 26.10.2017.
 */
public class AuctionResultDto {

    private String id;
    private String title;
    private Boolean isNew;
    private String editorContent;
    private BigDecimal price;
    private BigDecimal bidStartingPrice;
    private BigDecimal bidMinimalPrice;
    private Integer amount;
    private Integer currentAmount;
    private Boolean isBuyout;
    private Boolean isBid;
    private Boolean hasEnded;

    @JsonIgnore
    private Category category;

    private String createDate;
    private String endDate;
    private String endDateDotFormat;
    private UserResultDto sellerDto;
    private TransactionResultDto winningBid;
    private Integer biddingUsersAmount;
    private Integer buyoutUsersAmount;
    private String mainImage;
    private Integer positiveCommentPercent;
    private Boolean untilOufOfItems;
    private BigDecimal shippingTimeRatingAverage;
    private BigDecimal shippingCostRatingAverage;
    private BigDecimal descriptionAccordanceRatingAverage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEditorContent() {
        return editorContent;
    }

    public void setEditorContent(String editorContent) {
        this.editorContent = editorContent;
    }

    public Boolean getIsBuyout() {
        return isBuyout;
    }

    public void setIsBuyout(Boolean buyout) {
        isBuyout = buyout;
    }

    public Boolean getIsBid() {
        return isBid;
    }

    public void setIsBid(Boolean bid) {
        isBid = bid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean aNew) {
        isNew = aNew;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getBidStartingPrice() {
        return bidStartingPrice;
    }

    public void setBidStartingPrice(BigDecimal bidStartingPrice) {
        this.bidStartingPrice = bidStartingPrice;
    }

    public BigDecimal getBidMinimalPrice() {
        return bidMinimalPrice;
    }

    public void setBidMinimalPrice(BigDecimal bidMinimalPrice) {
        this.bidMinimalPrice = bidMinimalPrice;
    }

    public Boolean getHasEnded() {
        return hasEnded;
    }

    public void setHasEnded(Boolean hasEnded) {
        this.hasEnded = hasEnded;
    }

    public UserResultDto getSellerDto() {
        return sellerDto;
    }

    public void setSellerDto(UserResultDto sellerDto) {
        this.sellerDto = sellerDto;
    }

    public TransactionResultDto getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(TransactionResultDto winningBid) {
        this.winningBid = winningBid;
    }

    public Integer getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(Integer currentAmount) {
        this.currentAmount = currentAmount;
    }

    public Integer getBiddingUsersAmount() {
        return biddingUsersAmount;
    }

    public void setBiddingUsersAmount(Integer biddingUsersAmount) {
        this.biddingUsersAmount = biddingUsersAmount;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Integer getPositiveCommentPercent() {
        return positiveCommentPercent;
    }

    public void setPositiveCommentPercent(Integer positiveCommentPercent) {
        this.positiveCommentPercent = positiveCommentPercent;
    }

    public Boolean getUntilOufOfItems() {
        return untilOufOfItems;
    }

    public void setUntilOufOfItems(Boolean untilOufOfItems) {
        this.untilOufOfItems = untilOufOfItems;
    }

    public Integer getBuyoutUsersAmount() {
        return buyoutUsersAmount;
    }

    public void setBuyoutUsersAmount(Integer buyoutUsersAmount) {
        this.buyoutUsersAmount = buyoutUsersAmount;
    }

    public BigDecimal getShippingTimeRatingAverage() {
        return shippingTimeRatingAverage;
    }

    public void setShippingTimeRatingAverage(BigDecimal shippingTimeRatingAverage) {
        this.shippingTimeRatingAverage = shippingTimeRatingAverage;
    }

    public BigDecimal getShippingCostRatingAverage() {
        return shippingCostRatingAverage;
    }

    public void setShippingCostRatingAverage(BigDecimal shippingCostRatingAverage) {
        this.shippingCostRatingAverage = shippingCostRatingAverage;
    }

    public BigDecimal getDescriptionAccordanceRatingAverage() {
        return descriptionAccordanceRatingAverage;
    }

    public void setDescriptionAccordanceRatingAverage(BigDecimal descriptionAccordanceRatingAverage) {
        this.descriptionAccordanceRatingAverage = descriptionAccordanceRatingAverage;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getEndDateDotFormat() {
        return endDateDotFormat;
    }

    public void setEndDateDotFormat(String endDateDotFormat) {
        this.endDateDotFormat = endDateDotFormat;
    }
}
