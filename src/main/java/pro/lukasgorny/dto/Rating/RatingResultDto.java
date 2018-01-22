package pro.lukasgorny.dto.Rating;

/**
 * Created by lukaszgo on 2018-01-22.
 */
public class RatingResultDto {
    private String content;
    private String ratingType;
    private String date;
    private String auctionName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }
}
