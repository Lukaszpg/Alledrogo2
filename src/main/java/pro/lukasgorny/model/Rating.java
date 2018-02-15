package pro.lukasgorny.model;

import javax.persistence.*;

import pro.lukasgorny.enums.RatingTypeEnum;

/**
 * Created by lukaszgo on 2018-01-11.
 */

@Entity
@Table(name = "ratings")
public class Rating extends Model {

    private String content;
    private RatingTypeEnum ratingTypeEnum;
    private User issuer;
    private User receiver;
    private Transaction transaction;
    private Integer shippingTimeRating;
    private Integer shipmentCostRating;
    private Integer descriptionAccordanceRating;

    @Lob
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Enumerated(EnumType.STRING)
    public RatingTypeEnum getRatingTypeEnum() {
        return ratingTypeEnum;
    }

    public void setRatingTypeEnum(RatingTypeEnum ratingTypeEnum) {
        this.ratingTypeEnum = ratingTypeEnum;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "issuer_id")
    public User getIssuer() {
        return issuer;
    }

    public void setIssuer(User issuer) {
        this.issuer = issuer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id")
    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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
