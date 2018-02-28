package pro.lukasgorny.service.paycheck;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.paycheck.PaycheckResultDto;
import pro.lukasgorny.model.Paycheck;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.PaycheckRepository;
import pro.lukasgorny.service.hash.HashService;
import pro.lukasgorny.service.user.UserService;

/**
 * Created by Łukasz on 28.02.2018.
 */
@Service
public class GetPaycheckServiceImpl implements GetPaycheckService {

    private final PaycheckRepository paycheckRepository;
    private final UserService userService;
    private final HashService hashService;
    private final MessageSource messages;

    @Autowired
    public GetPaycheckServiceImpl(PaycheckRepository paycheckRepository, UserService userService, HashService hashService, MessageSource messages) {
        this.paycheckRepository = paycheckRepository;
        this.userService = userService;
        this.hashService = hashService;
        this.messages = messages;
    }

    @Override
    public Paycheck getByPayPalPaymentId(String paypalPaymentId) {
        return paycheckRepository.findByPayPalPaymentId(paypalPaymentId);
    }

    @Override
    public List<Paycheck> getByTransactionId(Long id) {
        return paycheckRepository.findByTransactionId(id);
    }

    @Override
    public List<PaycheckResultDto> getByPayerEmail(String email) {
        User user = userService.getByEmail(email);
        return createDtoListFromEntityList(paycheckRepository.findByPayerId(user.getId()));
    }

    @Override
    public List<PaycheckResultDto> getFinishedByReceiverEmail(String email) {
        User user = userService.getByEmail(email);
        return createDtoListFromEntityList(paycheckRepository.findCompletedByReceiverId(user.getId()));
    }

    private List<PaycheckResultDto> createDtoListFromEntityList(List<Paycheck> paychecks) {
        return paychecks != null ? paychecks.stream().map(this::createDtoFromEntity).collect(Collectors.toList()) : new ArrayList<>();
    }

    private PaycheckResultDto createDtoFromEntity(Paycheck paycheck) {
        PaycheckResultDto paycheckResultDto = new PaycheckResultDto();
        paycheckResultDto.setAmount(paycheck.getCash());
        paycheckResultDto.setAuctionTitle(paycheck.getTransaction().getAuction().getTitle());
        paycheckResultDto.setReceiverName(paycheck.getReceiver().getEmail());
        paycheckResultDto.setPayerName(paycheck.getPayer().getEmail());
        paycheckResultDto.setPaycheckType(translatePaycheckType(paycheck));
        paycheckResultDto.setAuctionId(hashService.encode(paycheck.getTransaction().getAuction().getId()));
        return paycheckResultDto;
    }

    private String translatePaycheckType(Paycheck paycheck) {
        return  messages.getMessage("PaycheckType." + paycheck.getType().name(), null, LocaleContextHolder.getLocale());
    }
}
