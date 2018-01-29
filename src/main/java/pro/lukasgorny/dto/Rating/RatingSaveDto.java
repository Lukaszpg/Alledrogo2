package pro.lukasgorny.dto.Rating;

import javax.validation.constraints.NotNull;

/**
 * Created by lukaszgo on 2018-01-11.
 */
public class RatingSaveDto {

    @NotNull
    private String content;

    @NotNull
    private String ratingType;

    private String issuerName;
    private String transactionId;

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
}
