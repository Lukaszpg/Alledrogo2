package pro.lukasgorny.service.paycheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.paycheck.PaycheckSaveDto;
import pro.lukasgorny.model.Paycheck;
import pro.lukasgorny.repository.PaycheckRepository;

/**
 * Created by Łukasz on 28.02.2018.
 */
@Service
public class GetPaycheckServiceImpl implements GetPaycheckService {

    private final PaycheckRepository paycheckRepository;

    @Autowired
    public GetPaycheckServiceImpl(PaycheckRepository paycheckRepository) {
        this.paycheckRepository = paycheckRepository;
    }

    @Override
    public Paycheck getByPayPalPaymentId(String paypalPaymentId) {
        return paycheckRepository.findByPayPalPaymentId(paypalPaymentId);
    }

    private PaycheckSaveDto createDtoFromEntity(Paycheck paycheck) {
        PaycheckSaveDto paycheckSaveDto = new PaycheckSaveDto();
        paycheckSaveDto.setAuction(paycheck.getAuction());
        paycheckSaveDto.setPayer(paycheck.getPayer());
        paycheckSaveDto.setPaycheckType(paycheck.getType());
        paycheckSaveDto.setCash(paycheck.getCash());

        return paycheckSaveDto;
    }
}
