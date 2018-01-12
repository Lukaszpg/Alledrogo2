package pro.lukasgorny.dto.Rating;

import pro.lukasgorny.enums.RatingTypeEnum;

/**
 * Created by lukaszgo on 2018-01-11.
 */
public class RatingSaveDto {
    private String content;
    private RatingTypeEnum ratingType;
    private String auctionId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RatingTypeEnum getRatingType() {
        return ratingType;
    }

    public void setRatingType(RatingTypeEnum ratingType) {
        this.ratingType = ratingType;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }
}
