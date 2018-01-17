package pro.lukasgorny.dto.Rating;

import pro.lukasgorny.enums.RatingTypeEnum;

/**
 * Created by lukaszgo on 2018-01-11.
 */
public class RatingSaveDto {
    private String content;
    private RatingTypeEnum ratingType;
    private String issuerName;
    private String transactionId;

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
}
