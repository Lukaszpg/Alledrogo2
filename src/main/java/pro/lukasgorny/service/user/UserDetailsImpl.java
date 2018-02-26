package pro.lukasgorny.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pro.lukasgorny.dto.user.UserDetailsAdapter;
import pro.lukasgorny.model.Role;
import pro.lukasgorny.model.User;
import pro.lukasgorny.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Łukasz on 25.10.2017.
 */

@Service
public class UserDetailsImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("No user found with username: " + email);
            }

            if (user.getBlocked()) {
                throw new RuntimeException("blocked");
            }

            return new UserDetailsAdapter(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
