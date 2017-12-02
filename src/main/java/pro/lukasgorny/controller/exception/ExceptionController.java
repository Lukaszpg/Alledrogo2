package pro.lukasgorny.controller.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.lukasgorny.util.Templates;
import pro.lukasgorny.util.Urls;

/**
 * Created by Łukasz on 23.11.2017.
 */
@Controller
public class ExceptionController {

    @RequestMapping(Urls.ERROR_404)
    public String notFoundError() {
        return Templates.ERROR_404;
    }

    @RequestMapping(Urls.ERROR_405)
    public String methodNotSupported() {
        return Templates.ERROR_405;
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(Urls.ERROR_500)
    public String generalError() {
        return Templates.ERROR_500;
    }

}
