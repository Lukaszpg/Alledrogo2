package pro.lukasgorny.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Łukasz on 25.10.2017.
 */
@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final MessageSource messages;

    @Autowired
    public CustomAuthenticationFailureHandler(MessageSource messages) {
        this.messages = messages;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);

        Locale locale = LocaleContextHolder.getLocale();

        String errorMessage = messages.getMessage("error.email.or.password.invalid", null, locale);

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
            errorMessage = messages.getMessage("error.user.disabled", null, locale);
        } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            errorMessage = messages.getMessage("error.user.expired", null, locale);
        } else if (exception.getMessage().equalsIgnoreCase("blocked")) {
            errorMessage = messages.getMessage("error.user.blocked", null, locale);
        }

        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }
}
