package pro.lukasgorny.controller.util;

import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pro.lukasgorny.config.Application;
import pro.lukasgorny.dto.util.VersionDto;
import pro.lukasgorny.util.Urls;

/**
 * Created by lukaszgo on 2017-10-26.
 */

@RestController
public class UtilRestController {

    private final Application application;
    private final MessageSource messageSource;

    @Autowired
    public UtilRestController(Application application, MessageSource messageSource) {
        this.application = application;
        this.messageSource = messageSource;
    }

    @GetMapping(Urls.Util.VERSION)
    public ResponseEntity<VersionDto> version() {
        VersionDto versionDto = new VersionDto();
        versionDto.setVersion(application.getVersion());
        versionDto.setMessage(messageSource.getMessage("version.tooltip", null, LocaleContextHolder.getLocale()));
        versionDto.setCompilationDate(
                messageSource.getMessage("compilation.date.tooltip", null, LocaleContextHolder.getLocale()) + " " + getFormattedDate());
        return new ResponseEntity<>(versionDto, null, HttpStatus.OK);
    }

    private String getFormattedDate() {
        String pattern = "dd-MM-yyyy HH:mm:ss.SSS";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(application.getCompilationDate());
    }
}
