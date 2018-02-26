package pro.lukasgorny.handler;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import pro.lukasgorny.model.User;
import pro.lukasgorny.service.user.UserService;
import pro.lukasgorny.util.Urls;

/**
 * Created by lukaszgo on 2018-02-26.
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final UserService userService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    public CustomAccessDeniedHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e)
            throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, determineTargetUrl(authentication));
    }

    private String determineTargetUrl(Authentication authentication) {
        User user = userService.getByEmail(authentication.getName());

        if (user.getUsing2FA() && userOnlyPreAuthorized(authentication)) {
            return Urls.Login.CODE;
        } else if (!user.getUsing2FA()) {
            return Urls.ERROR_403;
        } else {
            throw new IllegalStateException();
        }
    }

    private boolean userOnlyPreAuthorized(Authentication authentication) {
        List<GrantedAuthority> filteredAuthorities = authentication.getAuthorities().stream()
                                                                   .filter(authority -> authority.getAuthority().equals("ROLE_PRE_AUTH_USER"))
                                                                   .collect(Collectors.toList());

        return filteredAuthorities != null && filteredAuthorities.size() > 0;
    }
}
