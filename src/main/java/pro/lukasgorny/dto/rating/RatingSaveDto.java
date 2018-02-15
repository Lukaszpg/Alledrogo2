package pro.lukasgorny.dto.rating;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by lukaszgo on 2018-01-11.
 */
public class RatingSaveDto {

    @NotBlank
    private String content;

    @NotNull
    private String ratingType;

    private String issuerName;
    private String transactionId;

    private Integer shippingTimeRating;
    private Integer shipmentCostRating;
    private Integer descriptionAccordanceRating;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRatingType() {
        return ratingType;
    }

    public void setRatingType(String ratingType) {
        this.ratingType = ratingType;
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
