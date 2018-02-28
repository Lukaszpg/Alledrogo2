package pro.lukasgorny.dto.paycheck;

/**
 * Created by lukaszgo on 2018-02-28.
 */
public class PaymentCompleteDto {
    private String payerId;
    private String paymentId;

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
