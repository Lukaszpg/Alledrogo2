package pro.lukasgorny.service.paycheck;

import pro.lukasgorny.dto.paycheck.PaycheckSaveDto;
import pro.lukasgorny.model.Paycheck;

/**
 * Created by Łukasz on 28.02.2018.
 */
public interface CreatePaycheckService {
    void save(Paycheck paycheck);
    void create(PaycheckSaveDto paycheckSaveDto);
    void update(Paycheck paycheck);
}
