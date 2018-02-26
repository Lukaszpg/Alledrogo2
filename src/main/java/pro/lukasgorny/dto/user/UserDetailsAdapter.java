package pro.lukasgorny.dto.user;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import pro.lukasgorny.model.Role;
import pro.lukasgorny.model.User;

/**
 * Created by lukaszgo on 2018-02-26.
 */
public class UserDetailsAdapter implements UserDetails {

    private User user;

    public UserDetailsAdapter(User account) {
        this.user = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user.getUsing2FA()) {
            return AuthorityUtils.createAuthorityList("ROLE_PRE_AUTH_USER");
        } else {
            return addAllUserAuthorities();
        }
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }

    private List<GrantedAuthority> addAllUserAuthorities() {
        List<String> roleNames = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        return AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",", roleNames));
    }
}
