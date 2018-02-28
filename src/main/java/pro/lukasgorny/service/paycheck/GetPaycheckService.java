package pro.lukasgorny.service.paycheck;

import pro.lukasgorny.model.Paycheck;

/**
 * Created by Łukasz on 28.02.2018.
 */
public interface GetPaycheckService {
    Paycheck getByPayPalPaymentId(String paypalPaymentId);

}
