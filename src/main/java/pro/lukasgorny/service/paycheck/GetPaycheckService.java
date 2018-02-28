package pro.lukasgorny.service.paycheck;

import java.util.List;

import pro.lukasgorny.dto.paycheck.PaycheckResultDto;
import pro.lukasgorny.model.Paycheck;

/**
 * Created by Łukasz on 28.02.2018.
 */
public interface GetPaycheckService {
    Paycheck getByPayPalPaymentId(String paypalPaymentId);
    List<Paycheck> getByTransactionId(Long id);
    List<PaycheckResultDto> getByPayerEmail(String email);
    List<PaycheckResultDto> getFinishedByReceiverEmail(String email);
}
