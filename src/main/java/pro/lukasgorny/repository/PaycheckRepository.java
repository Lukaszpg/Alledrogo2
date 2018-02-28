package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.lukasgorny.model.Paycheck;
import pro.lukasgorny.util.QueryBody;

/**
 * Created by Łukasz on 28.02.2018.
 */
public interface PaycheckRepository extends JpaRepository<Paycheck, Long> {

    @Query(QueryBody.PaycheckQuery.FIND_BY_PAY_PAL_PAYMENT_ID)
    Paycheck findByPayPalPaymentId(@Param("paymentId") String payPalPaymentId);
}
