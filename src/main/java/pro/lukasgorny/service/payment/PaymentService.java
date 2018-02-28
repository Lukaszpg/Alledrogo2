package pro.lukasgorny.service.payment;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Łukasz on 27.02.2018.
 */
public interface PaymentService {

    Map<String, Object> createPayment(String sum);
    Map<String, Object> completePayment(HttpServletRequest req);
}
