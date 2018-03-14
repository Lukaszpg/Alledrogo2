package pro.lukasgorny.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pro.lukasgorny.handler.CustomAccessDeniedHandler;
import pro.lukasgorny.handler.CustomAuthenticationFailureHandler;
import pro.lukasgorny.handler.CustomAuthenticationSuccessHandler;

/**
 * Created by lukaszgo on 2017-05-25.
 */

@Configuration
@EnableWebSecurity
@ComponentScan("pro.lukasgorny")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource;

    @Autowired
    public WebSecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailsService userDetailsService,
            CustomAuthenticationFailureHandler authenticationFailureHandler, CustomAuthenticationSuccessHandler authenticationSuccessHandler,
            CustomAccessDeniedHandler accessDeniedHandler, CustomWebAuthenticationDetailsSource customWebAuthenticationDetailsSource) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userDetailsService = userDetailsService;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.accessDeniedHandler = accessDeniedHandler;
        this.customWebAuthenticationDetailsSource = customWebAuthenticationDetailsSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/activate/**").permitAll()
                .antMatchers("/token-error").permitAll()
                .antMatchers("/register-success").permitAll()
                .antMatchers("/register-success-qr-code").permitAll()
                .antMatchers("/reset-password-success").permitAll()
                .antMatchers("/version").permitAll()
                .antMatchers("/search").permitAll()
                .antMatchers("/regulations").permitAll()
                .antMatchers("/privacy").permitAll()
                .antMatchers("/search-rest").permitAll()
                .antMatchers("/reset-password").permitAll()
                .antMatchers("/user/change-password-success").permitAll()
                .antMatchers("/user/change-email-success").permitAll()
                .antMatchers("/user/security-change-success").permitAll()
                .antMatchers("/user/security-qr-code").permitAll()
                .antMatchers("/user/profile/**").permitAll()
                .antMatchers("/photo/get-all/**").permitAll()
                .antMatchers("/category-rest/**").permitAll()
                .antMatchers("/auction/get/**").permitAll()
                .antMatchers("/code").hasRole("PRE_AUTH_USER")
                .antMatchers("/payment/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/payment-rest/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/auction/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated().and().formLogin().authenticationDetailsSource(customWebAuthenticationDetailsSource).loginPage("/login")
                .usernameParameter("email").successHandler(authenticationSuccessHandler).failureHandler(authenticationFailureHandler).passwordParameter("password").and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/fonts/**", "/css/**", "/js/**", "/img/**");
    }

}