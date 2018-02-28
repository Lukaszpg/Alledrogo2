package pro.lukasgorny.service.payment;

import java.util.Map;

import pro.lukasgorny.dto.paycheck.PaymentCompleteDto;

/**
 * Created by Łukasz on 27.02.2018.
 */
public interface PaymentService {

    Map<String, Object> createPayment(String sum);
    Map<String, Object> completePayment(PaymentCompleteDto paymentCompleteDto);
}
