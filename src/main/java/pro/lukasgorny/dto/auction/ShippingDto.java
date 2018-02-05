package pro.lukasgorny.dto.auction;

import java.math.BigDecimal;

/**
 * Created by lukaszgo on 2018-02-05.
 */
public class ShippingDto {
    private Long shippingId;
    private BigDecimal moneyAmount;

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }
}
