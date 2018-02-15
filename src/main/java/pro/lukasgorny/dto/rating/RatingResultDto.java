package pro.lukasgorny.dto.rating;

/**
 * Created by lukaszgo on 2018-01-22.
 */
public class RatingResultDto {
    private String content;
    private String ratingType;
    private String date;
    private String auctionName;
    private Integer shippingTimeRating;
    private Integer shipmentCostRating;
    private Integer descriptionAccordanceRating;

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

    public Integer getShippingTimeRating() {
        return shippingTimeRating;
    }

    public void setShippingTimeRating(Integer shippingTimeRating) {
        this.shippingTimeRating = shippingTimeRating;
    }

    public Integer getShipmentCostRating() {
        return shipmentCostRating;
    }

    public void setShipmentCostRating(Integer shipmentCostRating) {
        this.shipmentCostRating = shipmentCostRating;
    }

    public Integer getDescriptionAccordanceRating() {
        return descriptionAccordanceRating;
    }

    public void setDescriptionAccordanceRating(Integer descriptionAccordanceRating) {
        this.descriptionAccordanceRating = descriptionAccordanceRating;
    }
}
