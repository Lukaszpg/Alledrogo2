package pro.lukasgorny.service.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;
import pro.lukasgorny.enums.MonthEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Łukasz on 23.10.2017.
 */

@Service
public class HelperServiceImpl implements HelperService {

    private final MessageSource messageSource;
    private final MessageSourceAccessor messageSourceAccessor;

    @Autowired
    public HelperServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
        messageSourceAccessor = new MessageSourceAccessor(messageSource, LocaleContextHolder.getLocale());
    }

    @Override
    public List<MonthEnum> prepareMonthsList() {
        List<MonthEnum> result = new ArrayList<>();
        MonthEnum[] array = MonthEnum.values();
        for(int i = 0; i < array.length; i++) {
            array[i].setTranslation(messageSourceAccessor.getMessage("label." + array[i].name().toLowerCase()));
            array[i].setMonthNumber(array[i].ordinal() + 1);
            result.add(array[i]);
       }

       return result;
    }
}
