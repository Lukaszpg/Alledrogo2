package pro.lukasgorny.dto.auction;

import java.math.BigDecimal;

/**
 * Created by Łukasz on 23.11.2017.
 */
public class OfferDto {
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
