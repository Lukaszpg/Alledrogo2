package pro.lukasgorny.service.paycheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.lukasgorny.dto.paycheck.PaycheckSaveDto;
import pro.lukasgorny.enums.PaycheckType;
import pro.lukasgorny.model.Paycheck;
import pro.lukasgorny.repository.PaycheckRepository;

/**
 * Created by Łukasz on 28.02.2018.
 */
@Service
public class CreatePaycheckServiceImpl implements CreatePaycheckService {

    private final PaycheckRepository paycheckRepository;

    @Autowired
    public CreatePaycheckServiceImpl(PaycheckRepository paycheckRepository) {
        this.paycheckRepository = paycheckRepository;
    }

    @Override
    public void save(Paycheck paycheck) {
        paycheckRepository.save(paycheck);
    }

    @Override
    public void create(PaycheckSaveDto paycheckSaveDto) {
        Paycheck paycheck = createFromDto(paycheckSaveDto);
        save(paycheck);
    }

    @Override
    public void update(Paycheck paycheck) {
        save(paycheck);
    }

    @Override
    public void cancel(String token) {
        Paycheck paycheck = paycheckRepository.findByToken(token);
        paycheck.setType(PaycheckType.CANCELED);
        save(paycheck);
    }

    private Paycheck createFromDto(PaycheckSaveDto paycheckSaveDto) {
        Paycheck paycheck = new Paycheck();
        paycheck.setPayer(paycheckSaveDto.getPayer());
        paycheck.setReceiver(paycheckSaveDto.getReceiver());
        paycheck.setTransaction(paycheckSaveDto.getTransaction());
        paycheck.setCash(paycheckSaveDto.getCash());
        paycheck.setType(paycheckSaveDto.getPaycheckType());
        paycheck.setPaypalTransactionId(paycheckSaveDto.getPaypalPaymentId());
        paycheck.setToken(paycheckSaveDto.getToken());
        return paycheck;
    }
}
